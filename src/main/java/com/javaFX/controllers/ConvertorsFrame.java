package com.javaFX.controllers;

import com.engine.calculators.NetworkConverter;
import com.engine.handlers.LanguageHandler;
import com.engine.utils.NetworkValidator;
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

    @FXML private Label label_requested_hosts;
    @FXML private JFXTextField txt_requested_hosts;
    @FXML private Label label_requested_hosts_cidr;
    @FXML private Label label_requested_hosts_netmask;
    @FXML private Label label_requested_hosts_class;
    @FXML private Label label_requested_hosts_subnets;
    @FXML private Label label_requested_hosts_available;
    @FXML private Label label_requested_hosts_wildcard;
    @FXML private JFXButton button_requested_hosts;

    @FXML private Label label_wildcard_first_ip;
    @FXML private JFXTextField txt_wildcard_first_ip;
    @FXML private JFXTextField txt_wildcard_last_ip;
    @FXML private Label label_wildcard_last_ip;
    @FXML private Label label_wildcard_result;
    @FXML private JFXButton button_wildcard;

    private static final String EXAMPLE_SHORT = LanguageHandler.getKey("word_example(short)");

    @FXML
    private void initialize() {
        System.out.println(getClass().getSimpleName() + ": Initialize");
        initIPv4Section();
        initNetmaskCidrSection();
        initRequestHostsSection();
        initWildcardSection();
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
        label_netmask_cidr_result.setText(LanguageHandler.getKey("converter_netmask_label_NetmaskCIDR")+":");
        label_netmask_cidr_result_class.setText(LanguageHandler.getKey("converter_netmask_label_Class")+":");
        label_netmask_cidr_result_hosts.setText(LanguageHandler.getKey("converter_netmask_label_Hosts")+":");
        label_netmask_cidr_result_subnets.setText(LanguageHandler.getKey("converter_netmask_label_Subnets")+":");
        label_netmask_cidr_result_wildcard.setText(LanguageHandler.getKey("converter_acl_wildcard")+":");
        button_netmask_cidr.setText(LanguageHandler.getKey("converter_netmask_button_ConverterNetmask"));
    }

    private void initRequestHostsSection() {
        label_requested_hosts.setText(LanguageHandler.getKey("converter_hosts_label_RequestedHosts"));
        txt_requested_hosts.setPromptText(EXAMPLE_SHORT + ": 60");
        label_requested_hosts_cidr.setText(LanguageHandler.getKey("converter_hosts_label_CIDR")+":");
        label_requested_hosts_netmask.setText(LanguageHandler.getKey("converter_hosts_label_Netmask")+":");
        label_requested_hosts_class.setText(LanguageHandler.getKey("converter_hosts_label_Class")+":");
        label_requested_hosts_available.setText(LanguageHandler.getKey("converter_hosts_label_Hosts")+":");
        label_requested_hosts_subnets.setText(LanguageHandler.getKey("converter_hosts_label_Subnets")+":");
        label_requested_hosts_wildcard.setText(LanguageHandler.getKey("converter_acl_wildcard")+":");
        button_requested_hosts.setText(LanguageHandler.getKey("converter_hosts_button_ConvertHosts"));
    }

    private void initWildcardSection() {
        label_wildcard_first_ip.setText(LanguageHandler.getKey("converter_acl_firstip")+":");
        txt_wildcard_first_ip.setPromptText(EXAMPLE_SHORT + ": 10.0.0.5");
        txt_wildcard_last_ip.setPromptText(EXAMPLE_SHORT + ": 10.0.0.41");
        label_wildcard_last_ip.setText(":"+LanguageHandler.getKey("converter_acl_lastip"));
        label_wildcard_result.setText(LanguageHandler.getKey("converter_acl_wildcard")+":");
        button_wildcard.setText(LanguageHandler.getKey("converter_acl_button_createwildcard"));
    }

    @FXML private void convert_ipv4() {
        System.out.println("Convert IPV4");
        String ip = txt_ipv4.getText();
        try {
            if (NetworkValidator.isBinaryInput(ip)) {
                label_ipv4_result.setText(LanguageHandler.getKey("converter_IPv4_label_Address")+": "+NetworkConverter.binaryIPv4ToDecimal(ip));
            } else {
                label_ipv4_result.setText(LanguageHandler.getKey("converter_IPv4_label_Address")+": "+NetworkConverter.decimalIPv4ToBinary(ip));
            }
        } catch (Exception ex) {
            //TODO popup alert --Invalid input
            System.out.println("Input is invalid");
        }
    }

    @FXML private void convert_netmask_cidr() {
        System.out.println("Convert netmask|cidr");
    }

    @FXML private void convert_requested_hosts() {
        System.out.println("Convert requested hosts");
    }

    @FXML private void convert_wildcard() {
        System.out.println("Convert wildcard");
    }
}
