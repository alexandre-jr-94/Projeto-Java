/*
|--------------------------------------------------------------------------
| Instruções
|--------------------------------------------------------------------------
|
| 1. Copiar este arquivo nessa mesma pasta Env do projeto
| 2. Renomear o arquivo novo para "Env.java" (não será commitado)
| 3. Preencher os campos abaixos de acordo com o ambiente
|
| Obs1.: Serão criadas constantes que funcionarão onde for necessário a adaptação do projeto ao ambiente de execução.
| Obs2.: Ao criar novas constantes no arquivo "Env.php" para utilização no sistema (a princípio apenas no arquivo DAO),
|        deve-se replicar essa alteração neste arquivo (env.php.example) e commitá-lo, para os outros ambientes adaptarem-se a alteração realizada
|
*/
package Env;

public abstract class Env {
    // Dados banco de dados
    public static final String ENV_SERVER   = "";
    public static final String ENV_DATABASE = "";
    public static final String ENV_USER     = "";
    public static final String ENV_PASSWORD = "";
}