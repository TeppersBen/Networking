package com.javaFX.controllers;

import com.engine.calculators.NetworkConverter;
import com.engine.handlers.LanguageHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ConvertorsFrame {

    @FXML private Label label_netmask_cidr;
    @FXML private JFXTextField txt_netmask_cidr;
    @FXML private Label label_netmask_cidr_result;
    @FXML private Label label_netmask_cidr_result_class;
    @FXML private Label label_netmask_cidr_result_hosts;
    @FXML private Label label_netmask_cidr_result_subnets;
    @FXML private Label label_netmask_cidr_result_wildcard;
    @FXML private JFXButton button_netmask_cidr;

    @FXML private Label label_ipv4;
    @FXML private JFXTextField txt_ipv4;
    @FXML private JFXButton button_ipv4;
    @FXML private Label label_ipv4_result;

    private static final String EXAMPLE_SHORT = LanguageHandler.getKey("word_example(short)");

    @FXML
    private void initialize() {
        System.out.println(getClass().getSimpleName() + ": Initialize");
        initIPv4Section();
        initNetmaskCidrSection();
    }

    private void initIPv4Section() {
        label_ipv4.setText(LanguageHandler.getKey("converter_IPv4_label_IPv4_Address") + ":");
        txt_ipv4.setPromptText(EXAMPLE_SHORT + ": [192.168.0.1 | 11000000.10101000.00000000.00000001]");
        button_ipv4.setText(LanguageHandler.getKey("converter_IPv4_button_ConvertAddress"));
        label_ipv4_result.setText(LanguageHandler.getKey("converter_IPv4_label_Address") + ":");
    }

    private void initNetmaskCidrSection() {
        label_netmask_cidr.setText(LanguageHandler.getKey("converter_netmask_label_NetmaskCIDR"));
        txt_netmask_cidr.setPromptText(EXAMPLE_SHORT + ": [24 | 255.255.255.0]");
        label_netmask_cidr_result.setText(LanguageHandler.getKey("converter_netmask_label_NetmaskCIDR")+": 255.255.255.255");
        label_netmask_cidr_result_class.setText(LanguageHandler.getKey("converter_netmask_label_Class")+":");
        label_netmask_cidr_result_hosts.setText(LanguageHandler.getKey("converter_netmask_label_Hosts")+":");
        label_netmask_cidr_result_subnets.setText(LanguageHandler.getKey("converter_netmask_label_Subnets")+":");
        label_netmask_cidr_result_wildcard.setText(LanguageHandler.getKey("converter_acl_wildcard")+":");
        button_netmask_cidr.setText(LanguageHandler.getKey("converter_netmask_button_ConverterNetmask"));
    }

    @FXML private void convert_ipv4() {
        System.out.println("Convert IPV4");
    }

    @FXML private void convert_netmask_cidr() { System.out.println("Convert netmask|cidr"); }
}
