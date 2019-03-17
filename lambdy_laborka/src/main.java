import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class main {




    public static void main(String[] args) {

        //przykład z interfejsem Runnable - lambda
        Thread watek=new Thread(()->{
            int i=0;

             for(;;) {
                 System.out.println(Thread.currentThread()+">>>> "+i++);

                 try {
                     Thread.sleep(500);
                 }catch (Exception e){}
             }
        });
        watek.start();

        Car car_1=new Car("Skoda","Fabia",60,5,45000);
        Car car_2=new Car("Seat","Ibiza",110,4,70000);
        Car car_3=new Car("Toyota","Yaris",46,4,40000);
        Car car_4=new Car("Volvo","xc60",190,5,180000);

        //przykład z interfejsem BiPredicate<x,y> - lambda
        car_1.comparePower((a,b)->( a.power>b.power),car_2 );

        //przykład z interfejsem BiPredicate<x> - przypisanie metody z innego obiektu
        pojazdRodzinny(car_4::isFamilyCar,car_4);

        //przykład z Function<x,y>
        zamien(main::konwersja,"414341");

        //przykład z BiFunction oraz konstruktorem.
        BiFunction<String,String,Car> carSupplier=Car::new;
        Car car=carSupplier.apply("Lexus","GS");
        System.out.println(car.brand + " "+car.model);





    }

    static Integer konwersja(String s)
    {
        return Integer.parseInt(s);
    }

    static void zamien(Function<String,Integer> integerStringFunction,String a)
    {
        System.out.println(integerStringFunction.apply(a).getClass());
    }


    static void pojazdRodzinny(Predicate<Car> carPredicate,Car car)
    {
        if (carPredicate.test(car)) System.out.println(car.brand+" "+car.model+" jest autem rodzinnym");
    }






}

class Car
{
    public Car() {
    }

    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public Car(String brand, String model, int power, int seats, double price) {
        this.brand = brand;
        this.model = model;
        this.power = power;
        this.seats = seats;
        this.price = price;
    }
    String brand;
    String model;
    int power;
    int seats;
    double price;

     void comparePower(BiPredicate<Car,Car> carPredicate,Car car2)
    {
      if (carPredicate.test(this,car2))System.out.println(this.brand+" "+this.model+" to szybszy samochód");
      else System.out.println(car2.brand+" "+car2.model+" to szybszy samochód");

    }

     boolean isFamilyCar(Car car)
    {
        if (car.seats>=5) return true;
        else return false;

    }

}