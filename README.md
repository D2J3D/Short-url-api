# Сокращатель ссылок
## О проекте
Бэкенд для сервиса сокращения ссылок.  
Пользователь посылает запрос, содержащий следующие параметры:  
* ```fullUrl``` -  длинный url, который должен быть сокращен
* ```expirationDate``` - дата, до которого будет действовать сокращенная сервисом ссылка. Чистка БД от просроченных ссылок проводится с помощью **Spring @Scheduled**  


## Стек
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)

## Локальный запуск
1. Клонирование репозитория
```sh  
git clone https://github.com/D2J3D/short-url-api.git
```
2. Сборка
```sh  
mvn package -Dmaven.test.skip.exec=true
```
3. Создание образа на основе Dockerfile
```sh  
docker build . -t shortenerapp:v1.0
```
4. И, наконец, запуск
```sh  
docker compose up
```