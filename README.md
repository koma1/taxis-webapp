# taxis-webapp

Java Web application

Formulation of the task:

Write a program that emulates a taxi service directory, with support for accessing data management operations via API (REST) and a custom Web module (simple web forms with MVC).
With authentication

Functionality:
  - REST:
      - Session stateless
      - HTTP-based authentication for rest
      - Full CRUD support
      - Output media formats: JSON, XML, Plain text, HTML
      - Input media formats: JSON, XML
      - User-friendly exceptions mapping
  - Security:
      - Captcha: For users than not have "active" session, any page (include login page) protected with captcha (for DDoS prevention)
      - CSRF protection (for any session-based CRUD)
      - Users authentication and privileges
  - Web:
      - Full CRUD for user-data
      - AJAX (jquery) some fields validation and indication

Stack:
    - Hibernate (+HQL, +Criteria API)
    - JAX-RS (Jersey)
    - Web 3.1 (Servlet, JSP, JSTL)
    - MVC: Apache Struts2 (+tiles)
    - Spring security
    
    - Lombok
    - CaGe (CAptcha image GEnerator)
