Тестовое задание “Справочник автомобилей”
REST сервис справочника автомобилей с хранением данных в PostreSQL.
Сервис принимает JSON параметры на Controller. В зависимости от URL имеется 4 входа в программу реализующие следующие сервисы:
1)	Save  методом POST URL: base/car/save на вход JSON объект с параметрами 
"carNumber": " номер автомобиля",
"brand": "бренд автомобиля",
"color": "цвет",
"yearOfIssue": год выпуска
Возвращает строку с подтверждением сохранения либо информацией что объект с таким номером уже существует.
2)	getInfo методом GET URL: base/car/info на вход JSON объект с любыми из перечисленных  параметров:
"id": id
"carNumber": " номер автомобиля",
"brand": "бренд автомобиля",
"color": "цвет",
"yearOfIssue": год выпуска
Опционально выдает все объекты у которых присутствует хотя бы один из выше указанных атрибутов или ошибку "Bad Request".
3)	delete методом  POST URL: base/car/{id}/delete принимает только id через переменную URL 
Возвратит строку с подтверждением удаления или ощибку с отсутсвием указанного id в базе данных.

4)	baseInfo методом GET URL: base/info на вход ничего не принимает 
Возвратит объект Base с информацией о таблице:
countCars число машин в базе;
lastEntry дата последнего добавления;
firstEntry дата первого добавления;
countColor список числа машин с совпадающим цветом;
countBrand список числа машин с совпадающим брендом;
