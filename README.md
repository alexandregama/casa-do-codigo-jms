# Casa do Código JMS - Ebook e Nota Fiscal
Projeto Casa do Código JMS para o curso de SOA da Caelum


Configuração do HornetQ
---------------------------
##### Precisamos criar um novo usuário para acessar a fila de mensagens do JMS do HornetQ

```bash
$ cd wildfly/bin
$ ./add-user
```

##### Acessar o admin do HornetQ e se autenticar

```
http://localhost:9990
```

Criando uma Queue
---------------------------
##### Selecione o menu

```
Configuration -> Messaging -> Destinations -> View -> Queue
```

##### Adicione uma Queue (para a geração de Ebook) com os seguintes dados

```
Name: FILA.GERADOR
JDNI Names: java:jboss/exported/jms/FILA.GERADOR
```

Criando um Topic
---------------------------
##### Selecione o menu

```
Configuration -> Messaging -> Destinations -> View -> Topic
```
##### Adicione um Topic (para o Financeiro) com os seguintes dados
```
name: TOPIC.LIVRARIA
JDNI Names: java:jboss/exported/jms/TOPIC.LIVRARIA
```



