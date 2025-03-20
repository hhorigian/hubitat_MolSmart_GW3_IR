Pode ser aprendido o controle remoto diretamente no iDoor usando a biblioteca de AC no iDoor, e enviar os comandos direto para o iDoor dos botões cadastrados em ele. 

Para usar esse Controle para AC já criado no Idoor aproveitando a biblioteca. 

1- Cadastrar o controle no idoor
2- Pegar o CID do controle remoto: https://www.youtube.com/watch?v=08Qb6pkxawM

3- Instalar o driver via HPM ou manulamente aqui do github. 
Dados do GW3: 
- IP do GW3
- Numero de Serie; 
- Código de Verificação (esses dados estão na etiqueta na parte de baixo do gateway); 
- Porta IR que vai ser utilizada para comandar o equipamento infravermelho. (Porta 1 = Blaster)
- CID (passo 2)

PRECISA FAZER O INITIALIZE dentro do Device para ele pegar os dados certos. 

Para usar no controle remoto do IR do GW3 para iDoor para TV e Som no dashboard, adicionar botões.  
Aqui a referencia dos Botões para adicionar no Dashboard   

To use the GW3 IR remote control for iDoor for TV and Sound on the dashboard, add buttons.
Here is the reference for Buttons to add to the Dashboard

4- Adicionar o device no dashboard. 
Comandar ele com botões: 


	case 3 : auto(); break
	case 4 : heat(); break
	case 5 : cool(); break
        case 6 : fan(); break
        case 7 : dry(); break
        case 8 : fanAuto(); break                
        case 9 : fanOn(); break                
        case 10 : fanCirculate(); break    
        case 13 : fanAuto(); break    
        case 14 : fanLow(); break    
        case 15 : fanMed(); break    
        case 16 : fanHigh(); break  

