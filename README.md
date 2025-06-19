# Secure Methods Task
Spring Boot приложение с защитой методов через Spring Security.

## Запуск
1. Установите Maven и Java 17+.
2. Склонируйте репозиторий: `git clone <your-repo-url>`.
3. Перейдите в папку: `cd secureapp`.
4. Запустите: `mvn spring-boot:run`.
5. Откройте браузер: `http://localhost:8080/api/secure/read`.

## Пользователи
- `reader:password` (роль READ)
- `writer:password` (роль WRITE)
- `deleter:password` (роль DELETE)
- `multirole:password` (роли WRITE, DELETE)

## Эндпоинты
- `GET /api/secure/read` — только для роли READ.
- `GET /api/secure/write` — только для роли WRITE.
- `GET /api/secure/write-or-delete` — для ролей WRITE или DELETE.
- `GET /api/secure/user-specific?username=<name>` — только для совпадающего пользователя.
автор: art4000xxx
