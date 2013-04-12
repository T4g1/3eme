using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace WebGame
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "ISerSilver1" in both code and config file together.
    [ServiceContract]
    public interface ISerSilver1
    {
        [OperationContract]
        void DoWork();
    }
}
