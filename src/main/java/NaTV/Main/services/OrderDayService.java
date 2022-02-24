package NaTV.Main.services;

import NaTV.Main.models.entity.OrderDay;
import NaTV.Main.models.entity.OrderDetail;

import java.util.Date;

public interface OrderDayService {
    OrderDay saveOrderDay(Date date, OrderDetail orderDetail);
}
