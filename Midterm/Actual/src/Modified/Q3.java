   import java.util.*;
   
   class Case{
         int id;
   }

   class Contact{
      Case c1;
	  Case c2;
      String label;
   }

   class ImportedCase extends Case{
         String country;
   }

   class LocalCase extends Case{

   }

   class Cluster{
	   String name;
         List<Case> cases;
   }
