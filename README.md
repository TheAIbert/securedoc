# Secure Document Management System

Welcome to the Secure Document Management System! This robust and user-friendly application is designed to securely store, update, upload, and delete documents, addressing the need for a centralized and secure repository for sensitive information.

## Application Overview

The Secure Document Management System offers a comprehensive solution for organizations to securely store, manage, and collaborate on documents. With robust security measures and a user-friendly interface, it addresses the needs of businesses requiring a reliable and secure document management platform.

### Key Features

- **User Authentication and Authorization**: Secure access through user authentication.
- **Multi-Factor Authentication (MFA)**: Additional layer of security during login.
- **Access Control**: Role-based access control to define user privileges.
- **Document Upload**: User-friendly interface for uploading documents (DOC, DOCX, XLS, PDFs, etc.).
- **Document Search**: Powerful search feature to quickly locate documents based on metadata.
- **Audit Trail**: Detailed audit trail logging all user activities.

For a more detailed overview, refer to [application-overview.md](./application-overview.md).

## Functional Requirements

### User Account

- **New Account**: Users can create new accounts with email verification.
- **Reset Password**: Users can reset their passwords through a secure link.
- **MFA**: Setup and use of Multi-Factor Authentication for added security.
- **Log In**: Secure login with email, password, and MFA (if enabled).
- **Profile Management**: Update basic information, password, and account settings.

### Document Management

- **Document List**: Display and manage a list of uploaded documents.
- **Document Details**: View and update document details, download, and delete documents.
- **Access Control**: Role-based permissions for actions on documents.

### Audit Trail

- **Tracking**: Keep track of creation and updates of entities (users, documents, etc.).

For a complete list of functional requirements, refer to [requirements.md](./requirements.md).

## Technologies Used

- **Backend**: Spring Boot, Java 21, Spring Security, JWT
- **Testing**: JUnit, Spring Security Test
- **Frontend (planned)**: React (react-router-dom, react-toastify, react-axios, react-redux, immer, tailwindcss) 
- **Database**: PostgreSQL
- **Authentication**: Spring Security, JWT, Multi-Factor Authentication
- **Build Tools**: Maven
- **Libraries**: Lombok, Apache Commons Lang3
- **Version Control**: Git

## License
This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details.

