# hubitat_MolSmart_GW3_IR

- Existem 2 formas de utilizar o GW3 via IR. <br><br>
a) Podem ser enviados os códigos IR diretamente para ele usando alguma biblioteca (Global Caché ou outras), e enviar os comandos SENDIR direto para ele.
Para essa finalidade não precisa de nenhuma configuração adicional no Idoor. Só precisa ter o GW3 na rede, e os dados de acesso dele.
Para esse método, Escolher o APP+Driver correto: <br><br>
<strong>TV Com códigos Sendir</strong><br>
  +Hubitat_TRATO_MolSmart_GW3_IR-TV_Driver.groovy<br>
  +Hubitat_TRATO_MolSmart_GW3_IR-TV_App.groovy <br><br>
<strong>AC Com códigos Sendir</strong><br>
  +Hubitat_TRATO_MolSmart_GW3_IR-AC_Driver.groovy<br>
  +Hubitat_TRATO_MolSmart_GW3_IR-AC_App.groovy <br>
  
<br>
1- Instalar o código do Driver Hub. <br>
2- Instalar o código do APP no Hub.<br><br>
<br>
Antes de seguir para o passo 3, recomendamos já ter o Gateway instalado na rede, cadastrado no APP iDoor, e criado/aprendido o "Controle Remoto" como informa o manual
<br><br>
Manual de instruções do GW: https://bit.ly/manualgw3 <br>
Manual/API de integração do GW: https://bit.ly/apigw3 <br>
Video de instalação do GW: https://youtu.be/EIgOz2DrALA?si=8kOFyFPpvq7FjPaY <br>
Dicas de integração do GW: https://youtu.be/Ex9b1RNuMUs?si=Kt0DbUi9_nxtS-E5<br>
<br>

3. Instalar um novo "APP de usuário" no Hub, e seleccionar o MolSmart GW3 (IR TV).<br>
  a) Ingressar os dados do Gateway:Endereço IP - sempre recomendado fixar o IP, ou reservar no DHCP); Numero de Serie; Código de Verificação (esses dados estão na etiqueta na parte de baixo do gateway); e Porta IR que vai ser utilizada para comandar o equipamento infravermelho. <br>
  b) Por default, o driver vai criar um Device no Hub, com todos os comandos e botões. Agora precisa inserir em cada comando o código SENDIR correspondente.<br>
  c) Adicionar o device no Dashboard<br>
  d) Cada botão (comando do controle) pode ser accionado com os numeros que aparecem na configuração do device; ex: "Mute-Sendir(2)". O Mudo é o botão = 2 para ser accionado.<br>


b) Pode ser aprendido o controle remoto diretamente no iDoor usando a biblioteca existente em ele, e enviar os comandos direto para o iDoor dos botões em ele. 
- Driver em desenvolvimento. 
