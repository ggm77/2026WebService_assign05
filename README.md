# simple_note

## ③ Solution 분석

**Q1. POST /api/books 요청은 어떤 순서로 처리되나요?**

`BookController.create()` → `BookService.create()` → `MemoryBookRepository.save()` 순서로 처리됩니다. Service에서 `BookRequest`로 `Book`을 만들어 저장하고, 결과를 `BookResponse`로 바꿔 201로 반환합니다.

**Q2. BookRequest, Book, BookResponse의 역할은 무엇인가요?**

`BookRequest`는 클라이언트가 보내는 데이터(id 없음), `Book`은 저장소에 저장되는 객체, `BookResponse`는 클라이언트에게 돌려주는 데이터(id 포함)입니다.

**Q3. 새 데이터의 ID는 어디서 생성되나요?**

`MemoryBookRepository.save()`에서 `++sequence` 값을 `setId()`로 넣어줍니다. 삭제해도 sequence는 줄지 않기 때문에 삭제된 ID는 다시 사용되지 않습니다.

**Q4. 없는 ID를 요청하면 어떻게 404가 반환되나요?**

`BookService.findBook()`에서 `repository.findById(id)`가 빈 `Optional`을 반환하면 `orElseThrow()`로 `ResponseStatusException(HttpStatus.NOT_FOUND)`를 던집니다. `findById()`, `update()`, `delete()` 모두 `findBook()`을 거치기 때문에 세 경우 모두 404가 반환됩니다.

**Q5. Book은 어떻게 BookResponse로 변환되나요?**

`BookService.toResponse()`에서 `new BookResponse(b.getId(), ...)`로 변환합니다. `findAll()`은 `stream().map(this::toResponse).toList()`로 목록 전체를 변환합니다.

**Q6. 서버를 재시작하면 데이터는 어떻게 되나요?**

`MemoryBookRepository`의 `LinkedHashMap`에만 저장하기 때문에 재시작하면 데이터가 사라집니다. 직접 재시작해 보니 목록이 `[]`로 조회되었습니다.
