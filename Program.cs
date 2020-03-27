// Hello World Example
// JNBridge Sales Engineering
// Visual Studio 2019, .NET Core 3.1, Java 8

// Before attempting to run this demo, be sure to see included jnbridgeConfig.json and make path adjustments
// to insure the app can find your systems installed JVM(s) and other system dependent components.
// Demo assumes there is a valid JNBridgePro evaluation license in default location: "C:\\Program Files (x86)\\JNBridge\\JNBridgePro v10.1"

using System;
using com.dmn;
//using helloWorldDemo; 

namespace ConsoleApp
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Hello World from .NET Core!");

            //int a = 255;
            //int b = 2;
            var dt = new DMNRunner();
            
            //var result = dt.runNew("Fall",40);
            var result = dt.runBmi(58, 70);
            Console.WriteLine(result);
            Console.ReadLine();
            
        }
    }
}
