package projectJV;

import java.util.Timer;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimerTask;

/**
 * DateTimeLabelクラス　
 * 1秒ごとにフォーマットに合わせた形式で日付・時刻を表示するラベルクラス
 * @author yoshinobu
 */
class DateTimeLabel extends JLabel {
    DateTimeFormatter format;   // LocalDateTime の表示フォーマット
    /**
     * コンストラクタ
     * @param format    LocalDateTimeの表示フォーマット　例）yyyy-MM-dd HH:mm:ss
     * @param period    再描画周期　ミリ秒単位で指定
     */
    public DateTimeLabel(String format, int period){
        this.format = DateTimeFormatter.ofPattern(format);      // 表示フォーマットの設定
        Timer timer = new Timer();                              // タイマーの生成
        timer.schedule(new TimerTaskLabelInner(), 300, period);  // 周期実行するタスクをスケジュール登録
                    // インナークラスTimerTaskLabelInnerを 300ms後に1000msごとに実行
//        timer.schedule(new TimerTaskLabel(this), 300, cycle);   // 周期実行するタスクをスケジュール登録
                    // TimerTaskLabelを 300ms後に1000msごとに実行
    }
    public DateTimeLabel(String format){
        this(format, 1000);
    }
    public DateTimeLabel(){
        this("yyyy-MM-dd HH:mm:ss");    // デフォルトの表示形式
    }
    /**
     * 現在日時をformatに従って表示
     */
    public void showTime(){
        LocalDateTime now = LocalDateTime.now();
        this.setText(now.format(format));
    }
    /**
     * スケジュールされた間隔でメソッド呼出しを行う
     * インナークラス　 
     */
    class TimerTaskLabelInner extends TimerTask {
        @Override
        public void run(){
            showTime();
        }
    }
}

class TimerTaskLabel extends TimerTask {
    private DateTimeLabel dateTimeLabel;
    public TimerTaskLabel(DateTimeLabel dateTimeLabel) {
        this.dateTimeLabel = dateTimeLabel;
    }
    @Override
    public void run() {
        dateTimeLabel.showTime();
    }
}