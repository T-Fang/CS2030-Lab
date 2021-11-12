   import java.util.*;
   class Case{
         int id;
         List<Contact> contacts;
   }

   class Contact{
      Case c;
      String label;
   }

   class Imported extends Case{
         String country;
   }

   class Local extends Case{

   }

   class Cluster{
         List<Case> cases;
   }
