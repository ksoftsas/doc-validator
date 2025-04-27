# **Contribution Guidelines**  

Welcome to **KSoft's Document Validator**! We appreciate your interest in contributing to our open-source project. These guidelines will help you collaborate effectively.  

---

## **1. How to Contribute**  

### **1.1. Reporting Issues**  
Found a bug or have an enhancement idea?  
- **Search** existing issues to avoid duplicates.  
- **Create a new issue** with:  
  - **Title**: Clear and concise (e.g., "Bug: Chilean RUT validation fails for 'K' check digit").  
  - **Description**:  
    - Context (country, document type).  
    - Steps to reproduce (if a bug).  
    - Expected vs. actual behavior.  
  - **Labels**: Use `bug`, `enhancement`, or `documentation`.  

### **1.2. Submitting Pull Requests (PRs)**  
To contribute code:  
1. **Fork** the repository.  
2. **Create a branch**:  
   ```bash
   git checkout -b fix/chile-rut-validation
   ```  
3. **Make changes**: Follow coding standards (see **Section 2**).  
4. **Add tests** for new features/bug fixes.  
5. **Submit a PR**:  
   - **Title**: Describe changes (e.g., "Fix: Chilean RUT 'K' check digit support").  
   - **Description**: Explain the changes and reference related issues (#123).  
   - **Review**: Maintainers will provide feedback before merging.  

---

## **2. Coding Standards**  

### **2.1. Style & Conventions**  
- **Java**: Follow [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html).  
- **Spring Boot**: Use dependency injection and standard annotations.  
- **Documentation**:  
  - Add Javadoc for public methods.  
  - Update `README.md` for new features.  

### **2.2. Project Structure**  
```
src/
├── main/
│   ├── java/com/ksoft/validation/
│   │   ├── core/          # Validation logic (e.g., modulo checks)
│   │   ├── api/           # REST controllers
│   │   ├── model/         # DTOs (request/response)
│   │   └── util/          # Helper classes
│   └── resources/         # Configuration (application.yml)
├── test/                  # Unit/integration tests
```  

### **2.3. Testing**  
- **Coverage**: Maintain ≥80% test coverage for new code.  
- **Test Types**:  
  - **Unit tests**: Validate algorithms (e.g., `ChileanRutValidatorTest`).  
  - **Integration tests**: Test API endpoints (e.g., `DocumentValidationControllerIT`).  
- **Naming**:  
  ```java
  @Test
  void shouldReturnTrue_WhenValidMexicanCURP() { ... }
  ```  

---

## **3. Review Process**  
1. **Self-review**: Ensure your code meets guidelines.  
2. **Peer review**:  
   - Maintainers will comment on PRs.  
   - Address feedback before merging.  
3. **Merge**:  
   - PRs require ≥1 approval.  
   - CI/CD checks must pass.  

---

## **4. Recommended Tools**  
- **IDE**: IntelliJ IDEA or VS Code (with Java extensions).  
- **Build**: Maven (`mvn clean verify`).  
- **Formatting**: Use `google-java-format`.  
- **Git**: Write descriptive commit messages (e.g., `feat: add Peruvian DNI validation`).  

---

## **5. Code of Conduct**  
This project adheres to the [Contributor Covenant](https://www.contributor-covenant.org/). Be respectful and inclusive.  

---

## **Questions?**  
Open an issue with the `question` label or contact the maintainers.  

Thank you for contributing! 🌟  
