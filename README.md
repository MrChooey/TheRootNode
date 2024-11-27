# TheRootBar

TheRootBar is a social platform built using Java Swing, with a focus on user interaction through posts, comments, likes, and moderation. The project includes various user roles (user, moderator, and administrator) to enable dynamic content management and reporting functionality. It allows users to create posts, like/unlike posts, and report inappropriate content. Moderators can review reports and manage posts.

## Features

- **User Roles:**
  - **User:** Create posts, like/unlike posts, and view content.
  - **Moderator:** Review and manage reported posts.
  - **Administrator:** Manage users, including promoting/demoting roles.
  
- **Post Creation & Interaction:**
  - Users can create posts with titles and content.
  - Users can like/unlike posts and see the total number of likes.
  - Users can report inappropriate posts (handled by moderators).

- **Post Moderation:**
  - Moderators can view and manage reported posts.
  - They have the ability to delete posts or dismiss reports.

- **Live Clock:**
  - A live clock feature displays the current time and updates in real-time.

- **Dynamic Content Rendering:**
  - Content is dynamically loaded and updated with user interactions, including the ability to like, comment, and report posts.

## Technologies Used

- **Java Swing** for the graphical user interface.
- **MySQL** for database management.
- **JDBC** for connecting Java application to MySQL database.
- **Multithreading** for updating live clock.

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/TheRootBar.git
    ```

2. **Ensure you have Java and MySQL installed:**
   - Java: [Download Java](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
   - MySQL: [Download MySQL](https://dev.mysql.com/downloads/installer/)

3. **Set up your database:**
   - Create a database and tables based on the provided SQL script in the `database` folder. This script contains the necessary table definitions for users, posts, likes, reports, and roles.

4. **Run the project:**
   - Open the project in your IDE (e.g., IntelliJ, NetBeans).
   - Run the `Main` class or the entry point for the application.

## Usage

- Upon starting the application, you will be prompted to log in or register a new user.
- Once logged in, users can:
  - Create posts with titles and content.
  - Like and unlike posts.
  - Report inappropriate content.
  
- **Moderators** can:
  - View and manage reported posts.
  - Delete inappropriate posts or dismiss reports.

- The live clock feature will always display the current time at the top of the application and updates in real-time.

## Future Improvements

- Implement more advanced post management features, such as editing or deleting posts by users.
- Enhance the UI with more customization options.
- Add additional functionality for moderators and administrators, such as managing user roles directly and enhancing reporting features.

## Contributing

If you would like to contribute to this project, feel free to fork the repository and submit a pull request. Please make sure your changes are well-tested and documented.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

