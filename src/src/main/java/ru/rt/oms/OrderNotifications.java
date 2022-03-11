package ru.rt.oms;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderNotifications", propOrder = {
    "orderNotification"
})
public class OrderNotifications {

    protected List<OrderNotification> orderNotification;

    /**
     * Gets the value of the orderNotification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderNotification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderNotification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrderNotification }
     * 
     * 
     */
    public List<OrderNotification> getOrderNotification() {
        if (orderNotification == null) {
            orderNotification = new ArrayList<OrderNotification>();
        }
        return this.orderNotification;
    }

}
