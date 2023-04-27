# Module_1_3

Консольное CRUD приложение, которое имеет следующие сущности:

    Writer - (id, firstName, lastName, List<Post> posts)
    Post - (id, content, created, updated, List<Label> labels)
    Label - (id, name)
    PostStatus - (enum ACTIVE, UNDER_REVIEW, DELETED)

Каждая сущность имеет поле Status. В момент удаления, мы не удаляем запись из файла, а меняем её статус на DELETED.

В качестве хранилища данных необходимо использовать текстовые файлы:
writers.json, posts.json, labels.json

Пользователь в консоли должен иметь возможность создания, получения, редактирования и удаления данных.

Слои:
- model - POJO клаcсы
- repository - классы, реализующие доступ к текстовым файлам
- controller - обработка запросов от пользователя
- view - все данные, необходимые для работы с консолью