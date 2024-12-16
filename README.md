# gerenciador_de_projetos

Gerenciador de Tarefas de Projetos com RichFaces

Para executar este projeto com sucesso precisa dos seguintes requisitos:

1. JDK ou JRE versão 8 ou superior
2. MySQL versão 8.x
3. Maven versão 3.x
4. Servidor Aplicacional (Apache Tomcat - use este para um teste rápido, Jboss, WildFly)

Baixe este projeto para qualquer diretório do seu computador (O projeto está na branch master)
Acessa ao diretório do projeto e permance mesmo na raíz

Configure a base de dados, crie uma base de dados vazia pode atribuir qualquer nome a sua escolha.
Por exemplo: gerenciador_de_projetos
Acesse o arquivo "applicationContext.xml" localizado em src/main/resources e altere os seguintes valores:

<property name="jdbcUrl" value="jdbc:mysql://localhost:porta/basededados"/>
<property name="username" value="usuariodabasededados"/>
<property name="password" value="senhadabasededados"/>

porta - indique a porta de acesso à base de dados
basededados - indique o nome da base de dados vazia que criou
usuariodabasededados - o usuário que usa para aceder à base de dados
senhadabasededados - a senha de acesso à base de dados

Grave e feche o arquivo.

Agora volte a raíz do projeto e compile e gera o pacote com o seguinte comando no terminal ou linha de comando na raíz do projeto.

"mvn clean install" ou "mvn clean package"

Após a execução do comando acima, note que gerou-se uma pasta "target", pode acessar a essa pasta e copiar o arquivo war para o seu Servidor Aplicacional.

Por exemplo, para Apache Tomcat jogue o arquivo com a extensão .war na pasta webapps.

Antes de executar o projeto, lembre-se de deixar o Sistema de Gerenciamento de Base de Dados online ou rodando, após isso pode executar o servidor aplicacional e verá que o
Servidor Aplicacional abrirá automaticamente o navegador em http://localhost:8080/gerenciador_de_projetos (aqui a porta depende da configuração do seu servidor aplicacional)
e pode adicionar, editar, excluir e visualizar projetos e suas tarefas.

Caso o Servidor Aplicacional não abra automaticamente o projeto no navegador então pode aceder em http://localhost:8080/gerenciador_de_projetos (observe que a porta depende 
da configuração do seu servidor)
