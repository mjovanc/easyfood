package com.example.easyfood.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyfood.model.Order;
import com.example.easyfood.repository.OrderRepository;

import java.util.List;

/**
 * Represents the ViewModel of ManagerOrdersActivity
 */
public class ManagerOrdersViewModel extends ViewModel {
    private MutableLiveData<List<Order>> orders;
    private OrderRepository orderRepository;

    /**
     * Initializes the ViewModel
     *
     * @param eateryId : String - The id of the eatery
     */
    public void init(String eateryId) {
        if (orders != null) {
            return;
        }
        orderRepository = OrderRepository.getInstance();
        orders = orderRepository.getAllOrdersForSpecificEatery(eateryId);
    }

    /**
     * Returns a list of all orders
     *
     * @return orders - LiveData<List<Order>> - The list of orders
     */
    public LiveData<List<Order>> getOrders() {
        return orders;
    }

    /**
     * Get specific order  // TODO Should use LiveData instead
     *
     * @param position - int - The position in the list
     * @return order - Order - The order
     */
    public Order getOrder(int position) {
        return orders.getValue().get(position);
    }
}
