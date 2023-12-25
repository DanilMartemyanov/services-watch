# AppServer
- AppServer - это точка входа (Entry point). 

- На этом этапе происходит:
1. Creating a server (class: Server)
2. handlers registration (class: ServerMessageHandler)
3. Starting a server (class: Server)

# package: server

## Server
Интерфейс, описывающий возможности сервера.
Методы сервера требуют обработки исключения ServerException.

## SocketServer
Класс, реализующий Server.

## ConnectionListener
Класс, реализующий Runnable. 
Служит для считывания сообщений и отправки их в соответсвующий 
типу сообщения обработчик (class: ServerMessageHandler)

# package: handler
## ServerMessageHandler
Интерфейс, описывающий возможности обработчика.

## AbstractServerMessageHandler
Абстрактный класс, который частично реализует ServerMessageHandler.
Там только метод ServerMessageHandler#init() реализован. Это архитектурное решение
увидел у лектора. *Достоверно не могу знать, чем он руководствовался, когда это делал, 
но предположу, что это сделано потому что у нас все обработчики одинаково должны проводить инициализацию,
и ее решили сразу написать в этом абстрактном классе, чтобы не было повторение кода 
в случае если бы обработчик создавался, напрямую реализуя ServerMessageHandler.
Ну и в принципе в этом случае ServerMessageHandler#init - этот тот метод, реализацию которого можно сразу
написать*

## CalculateSumMessageHandler
Класс, расширяющий AbstractServerMessageHandler.
Просто пример обработчика, который обрабатывает конкретный тип сообщения.