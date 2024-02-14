# Урок 5. От простого к практике
1. Реализуйте удаление пользователей. РЕАЛИЗОВАНО.

2. Подумать, где должен находиться метод createUser из UserView и если 
получится, вынести его в нужный слой. Вынести логику dao в слой репозитория, 
а от слоя dao избавится физически(перенести нужный код в класс репозитория, 
а пакет dao удалить).

### На выбор (не обязательно):
3. подумайте как оптимизировать код приложения (например, хэшировать все данные,
а в файл писать только при выходе из приложения

4. Дописать код для оставшихся команд в Commands (можно реализовать сохранение списка USer)
ИЛИ ВНЕСИТЕ СВОИ ИЗМЕНЕНИЯ В ПРОЕКТ, КОТОРЫЕ КАЖУТЬСЯ ЛОГИЧНЫМИ ВАМ.
!!!! Пожалуйста о том что изменили, напишите в комментариях(например в ридми)

### Сделано:
1. удаление реализовано
2. * метод createUser перенесен в UserController, так там все действия с User-ом.
   * метод prompt сделан public static и вынесен в отдельный класс Scanner в папке util.
   * пакет dao изничтожен. Логика из него переехала в UserRepository

3. * Действия из Main перенесены в новый класс ViewRunner в метод run(), для защиты от редактирования последовательности
   * Добавил показ пользователя перед тем как редактировать или удалять. 


