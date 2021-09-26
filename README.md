# deneysiz-backend

Backend project for 'Deneysiz' (Test Free) mobile application, which shows brands that test their products on animals. It is a social responsibility project and made for <a href="https://www.deneyehayir.org/">'Deneye Hayır Charity'</a>.


<h3>Prerequisites</h3>

<ul>
<li>JDK.11</li>
<li>PostgreSQL</li>
</ul>

<h3>Technology Stack</h3>

<ul>
<li>Java 11</li>
<li>Spring Boot 2.5</li>
<li>Spring Web</li>
<li>Spring Data JPA</li>
<li>Spring Security</li>
<li>JWT</li>
<li>Hibernate</li>
<li>Maven</li>
<li>Lombok</li>
<li>PostgreSQL</li>
</ul>

### How To Build and Run

1) Clone the project
2) Install dependencies using Maven
3) Execute create_db.sql query under /resources/sql path. Beware that it uses default postresql user (postgres). Change this first in application.properties according to your configuration.
4) Start Spring Boot application
5) (Soon!) Use Swagger (or Postman under /resources/postman) to make requests http://<base_url>/swagger-ui.html