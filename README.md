Project Description: Furniture and Home Tools Reduction Platform
The Furniture and Home Tools Reduction Platform is a web application that facilitates the donation and reuse of unused furniture and tools. Users can upload items they no longer need, search for available items, and request or reserve them. The platform also allows donors to track their contributions and impact, fostering a community of sharing and sustainability.

- Technologies Used:
  
1 - Backend: Spring Boot (Java)

      . Provides REST APIs for user authentication, item management, and request handling.
      . Follows best practices with layered architecture using Controllers, Services, Repositories, and DTOs.
  
2- Frontend: React.js (JavaScript)

      . Provides an interactive UI for users to browse, upload, and manage items.
      . Modal components for login, registration, item details, and item creation/updating.
      . Axios for handling HTTP requests to the backend.
      . Database: MySQL

3- Axios for making API requests.

4- Postman for testing API endpoints.

![Screenshot (137)](https://github.com/user-attachments/assets/87570d7b-4c07-4196-bc7d-969a523e436f)
![Screenshot (138)](https://github.com/user-attachments/assets/32419aef-4012-4043-959f-5c96c899f168)


- Key Features:
   . User Registration and Login:
       - Users can register and log in with an email and password.
  ![Screenshot (139)](https://github.com/user-attachments/assets/ebba64e2-6def-4780-9a05-f28b541df306)

   . Furniture and Tools Listings:
       - Users can upload furniture or tools, providing details like title, description, category, availability, address, and an image.
       - Edit and delete options are available for uploaded items.
  ![Screenshot (143)](https://github.com/user-attachments/assets/c2bd5af6-58fd-4080-8d78-bd36406e4ff1)
  ![Screenshot (144)](https://github.com/user-attachments/assets/1b9fd697-4a89-46bb-a882-b01456d719dd)


    . Search and Filtering:
      - Users can search for items based on title, category, or city.
  ![Screenshot (138)](https://github.com/user-attachments/assets/886c1374-c69d-4da5-8bce-31309c87c3c9)

  . Request and Reservation System:
      - Users can reserve or request items, with notifications sent to both the owner and the requester.
      - Owners can see the list of people who have requested their items.
  ![Screenshot (141)](https://github.com/user-attachments/assets/5b93035d-232b-4948-86e2-667af12ada79)

   . User Dashboard:
      - Users can view their uploaded items, requested items, and donation history.
      - Summary statistics like the total number of donated items and the number of people helped.
![Screenshot (140)](https://github.com/user-attachments/assets/25dfb5c5-49fb-4d66-8fad-b79895e64076)


- API Endpoints Used:
    . User Authentication:
       - POST /api/auth/register: Registers a new user.
       - POST /api/auth/login: Logs in a user.
  
    . Furniture Management:
       - GET /api/furniture/all: Retrieves all furniture items.
       - GET /api/furniture/user/{userId}: Retrieves furniture items uploaded by a specific user.
       - POST /api/furniture/create/{userId}: Creates a new furniture item for a specific user.
       - PUT /api/furniture/update/{furnitureId}: Updates a furniture item.
       - DELETE /api/furniture/delete/{furnitureId}: Deletes a furniture item by ID.

     . Search and Filter:
       - GET /api/furniture/search: Searches for furniture based on title, category, and city.
  
     . Request and Reservation:
       - POST /api/request/create: Allows a user to request a specific furniture item.
       - GET /api/request/user/{userId}: Retrieves all requests made by a user.
       - GET /api/request/furniture/{furnitureId}: Retrieves requests for a specific item.

      . Profile Management:
       - GET /api/user/{userId}: Retrieves user details, including email and uploaded items.
       - POST /api/user/logout: Logs out the user and invalidates the session.
