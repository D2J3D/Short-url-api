# Сокращатель ссылок
## О проекте
Бэкенд для сервиса сокращения ссылок.  
Пользователь посылает запрос, содержащий следующие параметры:  
* ```fullUrl``` - длинный url, который должен быть сокращен;
* ```expirationDate``` - дата, до которого будет действовать сокращенная сервисом ссылка. Чистка БД от просроченных ссылок проводится с помощью **Spring @Scheduled**;   
  
На этапе мэппинга и сохранения в БД также добавляются и заполняются параметры:
* ```shortUrlKey``` - уникальный ключ, по которому производится redirect на оригинальный адрес - _fullUrl_. В проекте генерируется с помощью MD5.
* `createdAt` - дата сохранения ссылки;
* `clickAmount` - обновляемое с каждым запросом количество обращений к ссылке.  

## Endpoints
`PUT /short-api/shorten` - добавляет в БД новую ссылку с указанными в теле запроса параметрами  
`GET /short-api/{shortUrlKey}` - перенаправляет на оригинальный адрес  
`GET /short-api/full/{shortUrlKey}` - возвращает JSON-объект c информацией о ссылке (включая _fullUrl, expirationDate, shortUrlKey_ )  
`GET /short-api/full/view{shortUrlKey}` - возвращает лишь оригинальный адрес - fullUrl
## Стек
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)

