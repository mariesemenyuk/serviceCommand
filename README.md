# Service Command

Сервис Команда - SOAP микросервис для телеграм-бота учета рабочего времени.
Сервис организовывает добавление новых пользователей, хранение их данных, создание команд, добавление и удаление пользователей из команды.
Остальные микросервисы: \
Телеграм+роутер: https://github.com/whlerwn/TelegramBot \
сервис-бухгалтер: https://github.com/Gretchen-z/accountant \
сервис-нотификатор: https://github.com/gamakarenko/notification \
сервис-отправитель: https://github.com/Ramz3301/tgbot

### Описание SOAP API
WSDL http://localhost:8080/groups?wsdl

### Методы
saveUser - добавить пользователя \
createGroup - создать группу \
addUserToGroup - добавить пользователя в группу \
deleteUserFromGroup - удалить пользователя из группы \
getAllGroups - получить все существующие группы \
getAllUsersAndGroups - получить всех пользователей и их группу \
getUser - получить данные конкретного юзера
