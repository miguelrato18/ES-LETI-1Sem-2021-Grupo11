import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.trello4j.model.Board;
import org.trello4j.model.Card;
import org.trello4j.model.Member;

import java.util.ArrayList;
import java.util.List;

public class TrelloAPItest {

    TrelloAPI trello;
    String key;
    String token;
    String username;

    @BeforeEach
    public void create(){
        key = "a68d6a52bb47c46fb8e95a23af622976";
        token = "83e8a25eed6608419b70207e4a9aa57c9891771366f3e12b500184e39a611dc5\n";
        username ="filipegoncalves79";

        trello = new TrelloAPI(key, token, username);
    }


    @Test
    public void getUsernameTest(){
        Assertions.assertEquals(username,trello.getUsername());
    }

    @Test
    public void getBoardTest(){

        Board board = trello.getBoard("Projecto de Engenharia de Software");
        Board board2 = new Board();

        board2.setId("616051e05bc5ec42cb81508a");



        Assertions.assertEquals(board2.getId(),board.getId());


    }
    @Test
    public void getMembersTest(){

        Board board = trello.getBoard("Projecto de Engenharia de Software" );
        Member member1 = new Member();
        Member member2 = new Member();
        Member member3 = new Member();
        Member member4 = new Member();
         member1.setId("61605051aca1fb0bf953c1a2");
         member2.setId("614ddd02472d981f60076600");
         member3.setId("61604e0c5d60e53926dacf2a");
         member4.setId("614df15185a0dc62ff3ffae9");
        List<Member> members = new ArrayList<>();
        members.add(member1);
        members.add(member2);
        members.add(member3);
        members.add(member4);
        List <Member> membros = trello.getMembers(board);
        for(int i =0; i< membros.size(); i++) {
            Assertions.assertEquals(members.get(i).getId(), membros.get(i).getId());
        }
    }

    @Test
    public void getListsTest(){
        Board board = trello.getBoard( "Projecto de Engenharia de Software" );
        List <org.trello4j.model.List> lists = trello.getTrelloApi().getListByBoard(board.getId());
        List <org.trello4j.model.List> listas = trello.getLists(board);
        for(int i =0; i< listas.size(); i++) {
            Assertions.assertEquals(lists.get(i).getId(),listas.get(i).getId());
        }


    }

    @Test
   public void getCardsTest(){
        Board board = trello.getBoard( "Projecto de Engenharia de Software" );
        List <org.trello4j.model.List> lists = trello.getTrelloApi().getListByBoard(board.getId());
        List <org.trello4j.model.List> listas = trello.getLists(board);
        for(int i =0; i< listas.size(); i++) {
            List <Card> cards = trello.getTrelloApi().getCardsByList(lists.get(i).getId());
            List <Card> cartoes = trello.getCards(listas.get(i));
            for(int i2=0; i2< cartoes.size(); i2++){
                Assertions.assertEquals(cards.get(i2).getId(),cartoes.get(i2).getId());
            }
        }

    }

    @Test
    public void getItemsCompletedBySprintTest(){
        Board board = trello.getBoard( "Projecto de Engenharia de Software" );
        List <Card> cards = new ArrayList<>();
        Card card1 = new Card();
        Card card2 = new Card();
        Card card3 = new Card();
        card1.setId("616b1898dacc1021b84d939b");
        card2.setId("61605a7b4361810315a0fda4");
        card3.setId("616055bf28919d0724ac473b");
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        List <Card> cartoes = trello.getItemsCompletedBySprint(board,"Sprint #1");
        for(int i=0; i<cards.size();i++)
            Assertions.assertEquals(cards.get(i).getId(),cartoes.get(i).getId());
    }

    @Test
    public void getSprintDurationTest(){
        Board board = trello.getBoard( "Projecto de Engenharia de Software" );
        String s = trello.getSprintDuration(board,"Sprint #1");
        String date = " 09/10/2021 - 23/09/2021";
        Assertions.assertEquals(date,s);
    }

    @Test
    public void getSprintEndDateTest(){
        Board board = trello.getBoard( "Projecto de Engenharia de Software" );
        String s = trello.getSprintEndDate(board,"Sprint #1");
        String date = "23/09/2021";
        Assertions.assertEquals(date,s);
    }

    @Test
    public void getSprintStartDateTest(){
        Board board = trello.getBoard( "Projecto de Engenharia de Software" );
        String s = trello.getSprintStartDate(board,"Sprint #1");
        String date = " 09/10/2021";
        Assertions.assertEquals(date,s);
    }

    @Test
    public void getSprintDescTest(){
        Board board = trello.getBoard( "Projecto de Engenharia de Software" );
        List <String> desc = new ArrayList<>();
        desc.add("Sprint Retrospective: Neste sprint ficou por fazer a exporta????o das informa????es do GitHub para o ficheiro CSV; global 0.5 hours.");
        desc.add("Sprint Review: Neste srprint foram feitas as Informa????es/Tarefas relativas ao GitHub e foram elaborados os Testes unit??rios; global 0.5 hours.");
        desc.add("Sprint Planning: Reuni??o com o objectivo de planear as quais as tarefas a ser realisadas/desenvolvidas durante o Sprint 3, as tarefas a realizar s??o Informa????es estatisticas do Projecto, Informa????es/Tarefas relativas ao GitHub, Testes unit??rios e acabar as tarefas n??o desenvolvidas no sprint 2,  Dura????o: 23/11/2021 - 07/12/2021; global 2 hours.\n");
        desc.add("Sprint Retrospective: Neste sprint ficou por fazer a Implementa????o das API de acesso ao Github e ficou por acabar o desenvolvimento dos metodos para obter as Informa????es dos SPRINTS no Projecto; global 0.5 hours.");
        desc.add("Sprint Review: Neste srprint foi feita a Implementa????o das API de acesso ao Trello, foi acabada a GUI para as Login Keys do Trello/Github e foram implementados os metodos para obter as Informa????es basicas do Projecto; global 0.5 hours.");
        desc.add("Sprint Planning: Reuni??o com o objectivo de planear as quais as tarefas a ser realisadas/desenvolvidas durante o Sprint 2, as tarefas a realizar s??o Implementa????o das API de acesso ao Trello, Implementa????o das API de acesso ao Github, Informa????es basicas do Projecto, Informa????es dos SPRINTS no Projecto e GUI para as Login Keys do Trello/Github (2/2),  Dura????o: 07/11/2021 - 21/11/2021; global 2 hours.\n");
        desc.add("Sprint Retrospective: Por carga hor??ria excessiva, ficou por fazer parte da GUI para as Login Keys do Trello/Github, sendo que o prot??tipo foi apenas criado e implementado, ficando por aperfei??oar. Estas funcionalidades ser??o implementadas no sprint#2. Por isso ser?? necess??rio passar a ter em conta as atividades das restantes cadeiras, de forma a termos uma vis??o mais realista no planeamento do trabalho; global 0.5 hours.");
        desc.add("Sprint Review: Neste srprint foi feita a organiza????o do trello e a escolha da framework para desenvolvimento da GUI. Ficou por fazer parte da GUI para as Login Keys do Trello/Github, sendo o prot??tipo foi apenas criado e implementado, ficando por aperfei??oar; global 0.5 hours.");
        desc.add("Sprint Planning: Reuni??o com o objectivo de planear as quais as tarefas a ser realisadas/desenvolvidas durante o Sprint 1, as tarefas a realizar s??o Estudo da Framework para o desenvolvimento da GUI,  Organiza????o do Trello e GUI para as Login Keys do Trello/Github,  Dura????o: 09/10/2021 - 23/09/2021; global 2 hours.");
        List <String> s = trello.getSprintDescription(board);
        for(int i=0; i<s.size(); i++){
            Assertions.assertEquals(desc.get(i),s.get(i));
        }
    }

    @Test
    public void numberOfSprintsTest(){
        Board board = trello.getBoard( "Projecto de Engenharia de Software" );
        int n= trello.numberOfSprints(board);
        Assertions.assertEquals(3,n);
    }

    @Test
    public void getHoursOfWorkTest(){
        Board board = trello.getBoard( "Projecto de Engenharia de Software" );
        Member member = new Member();
        member.setId("614ddd02472d981f60076600");
        member.setUsername("filipegoncalves79");
        double hours = trello.getHoursOfWork(board,member,"Sprint #2");
        Assertions.assertEquals(28.0,hours);

    }
    @Test
    public void HumanResourcesCostBySprintTest(){
        Board board = trello.getBoard( "Projecto de Engenharia de Software" );
        Member member = new Member();
        member.setId("614ddd02472d981f60076600");
        member.setUsername("filipegoncalves79");
        double cost = trello.HumanResourcesCostBySprint(board,member,"Sprint #2");
        Assertions.assertEquals(560.0,cost);
    }

    @Test
    public void getCardOriginatedCommitsTest(){
        Board board = trello.getBoard( "Projecto de Engenharia de Software" );
        List <Card> cards = new ArrayList<>();
        Card card1 = new Card();
        Card card2 = new Card();
        Card card3 = new Card();
        Card card4 = new Card();
        Card card5 = new Card();
        Card card6 = new Card();
        Card card7 = new Card();
        Card card8 = new Card();
        Card card9 = new Card();
        card1.setId("616ec07da77944437cae8945");
        card2.setId("616ec266d811f31c2af15b71");
        card3.setId("6160581b6002138fe6883f20");
        card4.setId("616057da2c07bc5f1a667b81");
        card5.setId("6160579f46e7956bf66c913c");
        card6.setId("61605784c8801973f245bbd3");
        card7.setId("6188258c01e01b8c5311f261");
        card8.setId("618828410c579047202c81d5");
        card9.setId("616055bf28919d0724ac473b");
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        cards.add(card8);
        cards.add(card9);
        List <Card> cartoes = trello.getCardOriginatedCommits(board);

        for(int i=0; i<cards.size();i++)
            Assertions.assertEquals(cards.get(i).getId(),cartoes.get(i).getId());
    }

    @Test
    public void getCardNotOriginatedCommitsTest(){
        Board board = trello.getBoard( "Projecto de Engenharia de Software" );
        List <Card> cards = new ArrayList<>();
        Card card1 = new Card();
        Card card2 = new Card();
        card1.setId("616b1898dacc1021b84d939b");
        card2.setId("61605a7b4361810315a0fda4");
        cards.add(card1);
        cards.add(card2);
        List <Card> cartoes = trello.getCardNotOriginatedCommits(board);
        for(int i=0; i<cards.size();i++)
            Assertions.assertEquals(cards.get(i).getId(),cartoes.get(i).getId());

    }


    @Test
    public void numberOfCardsOriginatedCommitsTest(){
        Board board = trello.getBoard( "Projecto de Engenharia de Software" );
        int n = trello.numberOfCardsOriginatedCommits(board);
        Assertions.assertEquals(9,n);
    }

    @Test
    public void numberOfCardsNotOriginatedCommitsTest(){
        Board board = trello.getBoard( "Projecto de Engenharia de Software" );
        int n = trello.numberOfCardsNotOriginatedCommits(board);
        Assertions.assertEquals(2,n);

    }

    @Test
    public void getHoursWorkedForCardsThatOriginatedCommitsTest(){
        Board board = trello.getBoard( "Projecto de Engenharia de Software" );
        Member member = new Member();
        member.setId("614ddd02472d981f60076600");
        member.setUsername("filipegoncalves79");
        double cost = trello.getHoursWorkedForCardsThatOriginatedCommitsByMember(board,member);
        Assertions.assertEquals(60.0,cost);

    }

    @Test
    public void getHoursWorkedForCardsThatNotOriginatedCommitsTest(){
        Board board = trello.getBoard( "Projecto de Engenharia de Software" );
        Member member = new Member();
        member.setId("614ddd02472d981f60076600");
        member.setUsername("filipegoncalves79");
        double cost = trello.getHoursWorkedForCardsThatNotOriginatedCommitsByMember(board,member);
        Assertions.assertEquals(7.0,cost);

    }

    @Test
    public void costHoursWorkedForCardsThatOriginatedCommitsTest(){
        Board board = trello.getBoard( "Projecto de Engenharia de Software" );
        Member member = new Member();
        member.setId("614ddd02472d981f60076600");
        member.setUsername("filipegoncalves79");
        double cost = trello.costHoursWorkedForCardsThatOriginatedCommitsByMember(board,member);
        Assertions.assertEquals(1200.0,cost);

    }

    @Test
    public void costHoursWorkedForCardsThatNotOriginatedCommitsTest(){
        Board board = trello.getBoard( "Projecto de Engenharia de Software" );
        Member member = new Member();
        member.setId("614ddd02472d981f60076600");
        member.setUsername("filipegoncalves79");
        double cost = trello.costHoursWorkedForCardsThatNotOriginatedCommitsByMember(board,member);
        Assertions.assertEquals(140.0,cost);

    }

    @Test
    public void TotalCostHoursWorkedForCardsThatOriginatedCommitsTest(){
        Board board = trello.getBoard( "Projecto de Engenharia de Software" );
        double cost = trello.TotalCostHoursWorkedForCardsThatOriginatedCommits(board);
        Assertions.assertEquals(3820.0,cost);
    }

    @Test
    public void TotalCostHoursWorkedForCardsThatNotOriginatedCommitsTest(){
        Board board = trello.getBoard( "Projecto de Engenharia de Software" );
        double cost = trello.TotalCostHoursWorkedForCardsThatNotOriginatedCommits(board);
        Assertions.assertEquals(560.0,cost);
    }







}
