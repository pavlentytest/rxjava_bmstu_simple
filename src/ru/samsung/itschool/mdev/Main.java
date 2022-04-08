package ru.samsung.itschool.mdev;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class Main {

    public static void main(String[] args) throws InterruptedException {


        int a = 100;
        int b = 200;

        int c = a+b; // 300

        a = 500;
        b = 700;


        // Observable - наблюдаемый
        Observable<String> myObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                int i = 0;
                while(i < 5) {
                    emitter.onNext("Hello: " + i);
                    i++;
                }
                emitter.onComplete();
            }
        });

        // Observer - наблюдатель
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("Subscribed!!!");
            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println("Get value from Observable: "+s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println(e.toString());
            }

            @Override
            public void onComplete() {
                System.out.println("Completed!!!");
            }
        };
        myObservable.subscribe(observer);









	  // Thread - нити (потоки)
      /* Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
       t1.start();
        */
        // [+][-][+][-]
     //   MyThread t1 = new MyThread("+");
     //   MyThread t2 = new MyThread("-");
      //  t1.start();
      //  t2.start();
       // Thread.sleep(500);
      //  t1.setFlag(false);
      //  t1.join(); // <!- ждет остановки
      //  printMessage("1 stopped!");
    }

    public static Object key = new Object();

    public static void printMessage(String mess) {
       // synchronized (key){
            try {
                System.out.print("[");
                Thread.sleep(500);
                System.out.print(mess);
                Thread.sleep(500);
                System.out.print("]");
        //        key.notify();
         //       key.wait();
            } catch (InterruptedException e) {
         //       e.printStackTrace();
        //    }
        }
    }

}
class MyThread extends Thread {

    private String m;
    private boolean flag = true;

    public MyThread(String m) {
        this.m = m;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        while (flag) {
            Main.printMessage(this.m);
        }
    }
}

