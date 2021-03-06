# ToDoList

## Домашнее задание №1
Написать прототип приложения "Список задач". Задача описывается состоянием (сделано/не сделано) и описанием того, что надо сделать.
Ввод данных выполняется через консоль. Программа должна поддерживать 4 команды:
- add - добавление задачи
- print - печать списка задач
- toggle - изменение статуса задачи
- quit - завершение работы

На данном этапе программа должна хранить только одну задачу. Добавление новой задачи заменяет ту, которая в данный момент сохранена.
Данные хранить в оперативной памяти. Необходимо предусмотреть обработку ошибок: неправильный формат ввода данных, некорректные аргументы команд.

Для сборки использовать maven, репозиторий также должен содержать файл readme.md с описанием (которое сейчас вы и читаете).

### Описание команд

- #### add, Формат команды: add <описание задачи>

Описание задачи может содержать любые символы, кроме перевода строки.
Перевод строки (нажатие клавиши Enter) означает завершение ввода описания задачи.

- #### print, Формат команды: print [all]

Выводит на печать список задач.
По-умолчанию выводятся только невыполненные задачи, в случае если команда выполняется с аргументом all - печатаются все задачи.
Печатаются следующие поля: идентификатор (номер, в данном случае всегда "1"), который используется в команде toggle,
статус задачи (x - выполнена, " " - не выполнена), описание задачи.

- #### toggle, Формат команды: toggle <идентификатор задачи>

Переключает состояние задачи (с "выполнена" на "не выполнена" и наоборот) по идентификатору.

- #### quit, Формат команды: quit

Завершает работу программы

## Домашнее задание №2
Модифицировать приложение следующим образом:

- В приложении должен храниться список задач.
- Добавить команду delete для удаления задачи из списка.
- Добавить команду edit для редактирования задачи.
- Добавить команду search для поиска по подстроке.

На данном этапе программа должна хранить все задачи. Данные хранить в оперативной памяти.

### Описание команд

- #### search, Формат команды: search <подстрока>

Выводит на печать список задач, описание которых содержит подстроку.
Вывод на печать в формате аналогичном команде print.

- #### delete, Формат команды: delete <идентификатор задачи>

Удаляет задачу из списка задач.

- #### edit, Формат команды: edit <идентификатор задачи> <новое значение>

Меняет описание задачи по идентификатору на новое значение.

## Домашнее задание №3
Провести рефакторинг разработанного приложения, так чтобы классы соответствовали принципам SOLID.

## Домашнее задание №5
Провести рефакторинг кода, сделанного на предыдущих заданиях, таким образом, чтобы он использовал следующие шаблоны
проектирования (обязательно оба пункта, в пунктах - альтернативы):
- #### Singleton или Factory Method (Abstract Factory)
- #### Strategy или Template Method
Провести рефакторинг кода, сделанного на предыдущих заданиях, таким образом, чтобы архитектура приложения соответствовала многослойной архитектуре (как показано в лекции).
В случае, если рефакторинг не требуется составить краткую пояснительную записку, в которой показать (со ссылками на код),что требования 1 и 2 выполнены ранее.

## Домашнее задание №6
Подключить к проекту Spring Boot (можно воспользоваться Spring Initializr) и заменить инфраструктурный код на использование Spring.

## Домашнее задание №7
В реализованное ранее приложение переписать на web вместо консольного:
- #### Операции и доменная модель остаются теми же (создание, изменение описания, удаление, переключение статуса задачи, получение списка (взамен 2-х операций print и search))
- #### Хранение по-прежнему в памяти
- #### Режим работы по-прежнему однопользовательский (нет авторизации и привязки задач к пользователям)
REST API спроектировать самостоятельно (можно опираться на Паспорт микросервиса To Do List, но там намного больше всего)
### Дополнительное задание
Составить описание API на Swagger/Open API

## Домашнее задание №8
В реализованном ранее приложении сделать следующие доработки:
- #### Изменить способ хранения (использовать хранение в БД):
- #### Добавить необходимые аннотации
- #### Для доступа к данным использоваться spring-data-jpa
- #### Подключить Liquibase для управления миграциями схемы БД

## Домашнее задание №9
В реализованном ранее приложении реализовать многопользовательский режим:
- ### Добавить новую сущность в БД: пользователь
- ### Добавить связь между пользователем и его задачами
- ### Подключить spring-security: 
- Реализовать HTTP Basic аутентификацию для обычных пользователей
- Запретить доступ неаутентифицированных пользователей к задачам
- Добавить контроллер для управления пользователями для администраторов (варианты реализации: добавить роли для пользователей или добавить вторую конфигурацию и использовать только одного "зашитого" в программу администратора)
- ### Изменить логику работы всех методов: пользователь может работать только со своими задачами (чужих просто не видит)