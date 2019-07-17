# sd-atv-performingwithrmi
A simple example of performance with Java RMI

### Problemática
 - Partindo da solução usada na aplicação de concorrência (Atividade Colaborativa)
	- [Atividade de Concorrência na Manipulçao de Dados 01](https://github.com/romulo-soares/sd-atv-concorrencia-parte1)  e [Atividade de Concorrência na Manipulçao de Dados 02](https://github.com/romulo-soares/sd-atv-concorrencia-parte2)
- Utilize a topologia informada na imagem em anexo *(topologia-aplicacao.png*).
- O Gerenciador de Identidade possui a função única de CRIAR UM NOVO ID, nunca repetido e sequencial - deve ter um mecanismo para garantir que a identidade foi usada realmente.
- Use apenas **LocateRegistry** para registrar e encontrar os serviços (não use *Naming*) .
- Cada projeto deve representar uma aplicação.

#### Passos para Execução
 > 1. Execute no diretório docker o arquivo `docker-compose.yml` com o comando `docker-compose up -d` 
 para criar e inicializar o banco PostgreSQL
 > 2. Utilizando a IDE construa o projeto __`shared`__
 > 3. Utilizando a IDE construa e execute o projeto __`server-rmi`__ para inicializa o servidor
 > 4. Utilizando a IDE construa o projeto __`client-rmi`__ e execute duas instancias para inicializar dois clientes

#### Para Acesso ao Banco no Terminal
 > 1. Identifique o ID do container instanciado com o comando __`docker container ls`__
 > 2. Execute o comando __`docker exec -it <container_id> /bin/bash`__ para ter acesso a sessão do banco de modo iterativo com psql
 > 3. Para acessar o banco execute o comando __`psql -U postgres <bank_name>`__. `bank_name` = 'managementuser'.
 > 4. Pronto para executar comandos SQL seguidos de ponto e virgula (;).
