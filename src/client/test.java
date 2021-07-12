package client;
import java.util.Scanner;
import mypackage.WeatherWSLocator;
import mypackage.WeatherWSSoap_PortType;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

public class test {
    public static void main(String[] args) throws ServiceException, RemoteException{
        WeatherWSLocator wsl = new WeatherWSLocator();
        WeatherWSSoap_PortType wp = wsl.getWeatherWSSoap();
        Scanner scan = new Scanner(System.in);
        String name = scan.next();
        // 从键盘接收数据
        String[] place = wp.getWeather(name,null);
        for(String str:place){
            System.out.println(str);
        }
    }
}
