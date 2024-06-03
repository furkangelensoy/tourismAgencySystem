# Tourism Agency System
A tourism agency system. Admin can add new user, update or delete existing users. Also admin must select role (Admin or Employee) when adding new user. 
Employee can add new hotels, update or delete existing hotels.
Employee can create reservations. 
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

If you want, you can take a look at the application interface photos and the YouTube link on how to use the application below.

<details close>
<summary>Login</summary>
<br>

* You can login with username = "admin", password = "123" as administrator. Also if you want you can login with username = "user" password = "123" as employee.
* ![0](https://github.com/furkangelensoy/tourismAgencySystem/assets/134130366/c4b5a4c5-bdf6-4cc2-92f9-2e3512cb3eca)


</details>

<details close>
<summary>Admin Login</summary>
<br>

* Users list. You can also search user according to user's role.
* ![1](https://github.com/furkangelensoy/tourismAgencySystem/assets/134130366/9125d194-9c03-487e-a966-0c8eb9cf968e),
*  Add new user. 
* ![2](https://github.com/furkangelensoy/tourismAgencySystem/assets/134130366/4bb6359b-c013-4a83-a3f6-1c4d3d76fba9)
* Update selected user.
* ![3](https://github.com/furkangelensoy/tourismAgencySystem/assets/134130366/3ddc13b6-63e2-4090-8a4b-c833108d43c0)
* Delete selected user.
* ![4](https://github.com/furkangelensoy/tourismAgencySystem/assets/134130366/d9ed615e-9adb-49bb-b96a-18dfcbccc1c2)
* Logout from admin panel.
* ![6](https://github.com/furkangelensoy/tourismAgencySystem/assets/134130366/6415779a-06ae-4b36-adb1-9a5c3ce61596)

</details>

<details close>
<summary>Employee Login</summary>
<br>

<details close>
<summary>Hotel Management</summary>
<br>
  
* Hotel list.
* ![7](https://github.com/furkangelensoy/tourismAgencySystem/assets/134130366/44d84a3f-cc06-4c3d-be8c-def5801d74d4)
* Create new hotel.
* ![8](https://github.com/furkangelensoy/tourismAgencySystem/assets/134130366/cf344600-5622-4115-979e-11afb2f728e7)
* Update selected hotel.
* ![9](https://github.com/furkangelensoy/tourismAgencySystem/assets/134130366/a04c32e3-73df-4fa0-aa87-7b33e00783ae)



</details>

<details close>
<summary>Season Management</summary>
<br>

* Season list. You can also search season according to hotel name. 
* ![10](https://github.com/furkangelensoy/tourismAgencySystem/assets/134130366/9f3fdc30-fa9f-4406-b11c-30e632b2cc0b)
* Create new season.
* ![11](https://github.com/furkangelensoy/tourismAgencySystem/assets/134130366/6222d365-888a-4550-a8ab-075874bfc660)
* Update selected season.
* ![12](https://github.com/furkangelensoy/tourismAgencySystem/assets/134130366/9c050b11-81af-4ad5-820a-31758d25df93)

</details>

<details close>
<summary>Pension Management</summary>
<br>

* Pension list.
* ![13](https://github.com/furkangelensoy/tourismAgencySystem/assets/134130366/bf56a678-c5fc-4aa3-808b-0ac3e8d2204b)
* Create a new pension.
* ![14](https://github.com/furkangelensoy/tourismAgencySystem/assets/134130366/6af9b269-e19c-40a1-a2a8-fe0d1f172e7a)
* You can search pension according to hotel name.
* ![15](https://github.com/furkangelensoy/tourismAgencySystem/assets/134130366/2242189b-2c46-4dd5-a4f3-216c04825d68)


</details>

<details close>
<summary>Room Management</summary>
<br>

* Room list. Available rooms are listed here according to dates and stock.
* ![16](https://github.com/furkangelensoy/tourismAgencySystem/assets/134130366/3f89515b-cab7-4b0d-88f4-363d2d5eef20)
* You can search room according to hotel name or city or check-in date and check-out date
* ![17](https://github.com/furkangelensoy/tourismAgencySystem/assets/134130366/e1387af0-9374-4be2-ad4c-34ffbbd22afe)
* Create a new room.
* ![18](https://github.com/furkangelensoy/tourismAgencySystem/assets/134130366/38889f4a-71c1-4bd2-97de-e58516f88420)
* Update selected room.
* ![19](https://github.com/furkangelensoy/tourismAgencySystem/assets/134130366/95b3df48-480c-4fdc-8ade-b379cd7645ab)
* Create reservation to selected room.
* ![20](https://github.com/furkangelensoy/tourismAgencySystem/assets/134130366/afa8b22a-2433-4652-9f5c-e0b86f7424d9)

</details>

<details close>
<summary>Reservation Management</summary>
<br>

* Reservations are listed here.
* ![21](https://github.com/furkangelensoy/tourismAgencySystem/assets/134130366/46cfd0b7-5eb3-4d64-9da4-240c282a5b52)
* You can update or delete a reservation in this panel.
* ![22](https://github.com/furkangelensoy/tourismAgencySystem/assets/134130366/eb823938-a202-4432-980c-7a0629501e98)


</details>

</details>

## Youtube Link
https://www.youtube.com/watch?v=fQkvGRWC_jM

