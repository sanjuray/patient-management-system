<h1>🏥 Patient Management Microservices System</h1>

<p>
This project is a <strong>production-ready, cloud-native Patient Management System</strong> 
built using a <strong>microservices architecture</strong>. It demonstrates advanced backend engineering practices, 
including <em>distributed communication</em>, <em>secure authentication</em>, and 
<em>Infrastructure as Code (IaC)</em>.
</p>

<hr/>

<h2>🏗️ Architecture Overview</h2>

<p>
The system consists of several <strong>decoupled services</strong> managed via an 
<strong>API Gateway</strong>, designed for <strong>high availability</strong> and <strong>scalability</strong>.
</p>

<h3>⚙️ Key Technical Stack</h3>

<ul>
  <li><strong>Framework:</strong> Java Spring Boot</li>
  <li>
    <strong>Inter-service Communication:</strong>
    <ul>
      <li><strong>Synchronous:</strong> gRPC for low-latency billing operations</li>
      <li><strong>Asynchronous:</strong> Apache Kafka for event-driven analytics</li>
    </ul>
  </li>
  <li><strong>Security:</strong> Spring Security with JWT (JSON Web Tokens) for stateless authentication and granular authorization</li>
  <li><strong>Deployment & IaC:</strong> Docker containerization orchestrated with AWS CDK, deployed to LocalStack</li>
</ul>

<hr/>

<h2>🚀 Key Features</h2>

<ul>
  <li><strong>Patient Service:</strong> Manages CRUD operations for patient records with robust request validation</li>
  <li><strong>Billing Service:</strong> Handles financial records via gRPC</li>
  <li><strong>Analytics Service:</strong> Processes patient lifecycle events in real-time using Kafka</li>
  <li><strong>Auth Service:</strong> Provides secure login, token generation, and validation endpoints</li>
  <li><strong>API Gateway:</strong> Centralized routing, OpenAPI/Swagger documentation aggregation, and security filtering</li>
</ul>

<hr/>

<h2>🛠️ Infrastructure & Cloud</h2>

<p>
This project uses <strong>Infrastructure as Code (IaC)</strong> to ensure reproducible environments.
It provisions virtual infrastructure including:
</p>

<ul>
  <li><strong>VPC:</strong> Secure networking environment</li>
  <li><strong>RDS:</strong> Managed database instances</li>
  <li><strong>ECS:</strong> Container cluster management for production-like deployment</li>
</ul>

<hr/>

<h2>🧪 Testing Strategy</h2>

<p>
Reliability is ensured through a <strong>layered testing approach</strong>:
</p>

<ul>
  <li>
    <strong>Integration Tests:</strong> Verify cross-service functionality, including 
    authenticated requests via the API Gateway to downstream services
  </li>
</ul>

<hr/>

<p>
<em>Developed as part of a comprehensive microservices implementation project.</em>
</p>
