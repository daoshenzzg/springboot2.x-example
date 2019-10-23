package com.mgtv.demo.common.constants;

/**
 * 消息延时级别
 *
 * RocketMQ的延时等级为：1s，5s，10s，30s，1m，2m，3m，4m，5m，6m，7m，8m，9m，10m，20m，30m，1h，2h。
 *  * level=0，表示不延时。level=1，表示 1 级延时，对应延时 1s。level=2 表示 2 级延时，对应5s，以此类推。
 *
 * @author daoshenzzg@163.com
 * @date 2019-08-07 14:33
 */
public interface DelayLevel {
    int LEVEL_NO_DELAY = 0;
    int LEVEL_1S = 1;
    int LEVEL_5S = 2;
    int LEVEL_10S = 3;
    int LEVEL_30S = 4;
    int LEVEL_1M = 5;
    int LEVEL_2M = 6;
    int LEVEL_3M = 7;
    int LEVEL_4M = 8;
    int LEVEL_5M = 9;
    int LEVEL_6M = 10;
    int LEVEL_7M = 11;
    int LEVEL_8M = 12;
    int LEVEL_9M = 13;
    int LEVEL_10M = 14;
    int LEVEL_20M = 15;
    int LEVEL_30M = 16;
    int LEVEL_1H = 17;
    int LEVEL_2H = 18;
}
