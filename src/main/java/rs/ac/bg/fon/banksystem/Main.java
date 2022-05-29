package rs.ac.bg.fon.banksystem;

import rs.ac.bg.fon.banksystem.service.LegalEntityService;


public class Main {
    public static void main(String[] args) throws Exception {
       /* LegalEntity e=new LegalEntity();
        e.setIdentificationNumber("3233");
        e.setAccountNumber("32e");
        e.setName("Mini tini");
        Place p=new Place();
        p.setName("Šabac");
        Township t=new Township();
        t.setZipCode(17002L);
        t.setName("Mali Mokri lug");
        t.setPlace(p);
        Street s=new Street();
        s.setStreetName("Sabacka");
        s.setStreetNumber(30);
        s.setTownship(t);
        e.setStreet(s);
        LegalEntityService service=new LegalEntityService();
        service.save(e);*/

       /* LegalEntityService service=new LegalEntityService();
       LegalEntity entity=service.getById(2L);
       Township t=new Township();
       Place p=new Place();
       p.setName("Beograd");
       t.setName("mml");
       t.setZipCode(17000L);
       t.setPlace(p);
       entity.getStreet().setTownship(t);
       service.update(entity);*/
        LegalEntityService service=new LegalEntityService();
        System.out.println(service.getByValue("32"));







    }
}
