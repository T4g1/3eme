using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;
using System.Data.Common;
using System.Data.SqlClient;
using System.Collections;
using DataAccessLayer;

namespace Test
{
    class Program
    {
        
        static void Main(string[] args)
        {
            mainDal db = new mainDal();

            db.DeleteFromUsers("Pseudo", "toto");
            Console.ReadLine();
        }
    }
}
