# CrudConsole

## Описание

Необходимо реализовать консольное CRUD приложение, которое имеет следующие сущности: 

Project   
Category   
Customer  
ProjectStatus (enum ACTIVE, FINISHED, DELETED)  

Project -> Set<Category> categories + Customer customer   
Project -> ProjectStatus  

В качестве хранилища данных необходимо использовать текстовые файлы: 
projects.txt, categories.txt, customers.txt 

Пользователь в консоли должен иметь возможность создания, получения, редактирования и удаления данных. 

Слои:    
model - POJO клаcсы   
repository - классы, реализующие доступ к текстовым файлам  
controller - обработка запросов от пользователя   
view - все данные, необходимые для работы с консолью  

## Инструкция по запуску 

1) Скачать приложение 

2) Перейти в репозиторий по ссылке https://github.com/tigratius/CrudConsole 

3) Кликнуть зеленую кнопку "Clone or Download" в правой верхней части страницы. 

4) Распаковать архиватором 

5) Открыть проект в intellij idea 

6) Запустить класс CrudConsole
