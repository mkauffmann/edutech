Para rodar proj:

```
docker-compose up --build -d
```

API cria apenas usuários estudantes. Para dar permissão admin:

```
- Com o container rodando, acessar keycloak em http://localhost:8088/
- clicar em Administration console
- user admin / senha admin
- trocar para realm edutech-realm
- Clicar em Users na barra lateral
- Escolher um usuário para dar permissão
- Cliar no usuário, aba Role mapping
- Botão Assign Role, selecionar role ADMIN e cliar em assign
- No próximo login, usuário deve ter as permissões para endpoints administrativas
```

Ver collection do postman para contrato das APIs