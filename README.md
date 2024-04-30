# Tourism Agency System
A tourism agency system. Admin can add new user, update or delete existing users. Also admin must select role (Admin or Employee) when adding new user. 
You can add new hotels, update or delete existing hotels.
You can create reservations. 
Reservations can be made for available rooms according to dates. Reservations made can be cancelled or updated.

N layer architecture and java swing library were used in this project. This project consists of business, core, dao, entity and view packages. 
* **Business package:** This package acts as an intermediate between the View and the Dao.
* **Dao package:** This package includes database operations.
* **Entity package:** Contains classes that represent tables in the database
* **Core package:**  Db connection is made here. Helper classes are located here.
* **View package:** The layer at which users interact with the application and the final data will be visible to the users at this interface. It acts as an interface between the user and the application.

## Technologies
Project created with:
* Java Version 19
* PostgreSql Version 16

## How To Use
Clone this repository
```shell
https://github.com/furkangelensoy/tourismAgencySystem.git
```

<details close>
<summary>Login</summary>
<br>

* You can login with username = "admin", password = "123" as administrator. Also if you want you can login with username = "user" password = "123" as employee.

</details>


## İsterlere göre henüz tamamlanmamış işlemler;
Değerlendirme formu madde: 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23
