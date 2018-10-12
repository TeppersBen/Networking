package com.javaFX.controllers;

import com.engine.calculators.NetworkConverter;
import com.engine.calculators.VLSMSpecializedCalculator;
import com.engine.handlers.LanguageHandler;
import com.engine.utils.NetworkValidator;
import com.engine.utils.TickHandler;
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

    private static final int ERROR_TICK_LENGTH = 21;
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
            executeAlertMessage(button_ipv4, txt_ipv4, ex.getMessage(), ERROR_TICK_LENGTH);
            System.out.println(ex.getMessage());
        }
    }

    @FXML private void convert_netmask_cidr() {
        System.out.println("Convert netmask|cidr");
        String ip = txt_netmask_cidr.getText();
        try {
            //cidr/netmask -- class -- host -- subnet -- wildcard
            String[] data = new String[5];
            if (NetworkValidator.isCIDRNotation(ip)) {
                data[0] = NetworkConverter.netmaskCIDRtoDecimal(Integer.parseInt(ip));
                label_netmask_cidr_result.setText(LanguageHandler.getKey("converter_netmask_label_Netmask")+": " + data[0]);
            } else {
                data[0] = NetworkConverter.netmaskDecimalToCIDR(ip);
                label_netmask_cidr_result.setText(LanguageHandler.getKey("converter_netmask_label_CIDR")+": " + data[0]);
                data[0] = ip;
            }
            data[1] = NetworkConverter.getNetmaskClass(data[0]);
            data[2] = String.valueOf(
                    NetworkConverter.getTotalValidHosts(
                        Integer.parseInt(
                            NetworkConverter.netmaskDecimalToCIDR(data[0])
                        )
                    )
            );
            data[3] = String.valueOf(
                    NetworkConverter.getTotalValidSubnets(
                            Integer.parseInt(
                                    NetworkConverter.netmaskDecimalToCIDR(data[0])
                            )
                    )
            );
            data[4] = NetworkConverter.getWildCardMask(data[0]);
            label_netmask_cidr_result_class.setText(LanguageHandler.getKey("converter_netmask_label_Class")+": " + data[1]);
            label_netmask_cidr_result_hosts.setText(LanguageHandler.getKey("converter_netmask_label_Hosts")+": " + data[2]);
            label_netmask_cidr_result_subnets.setText(LanguageHandler.getKey("converter_netmask_label_Subnets")+": " + data[3]);
            label_netmask_cidr_result_wildcard.setText(LanguageHandler.getKey("converter_acl_wildcard")+": " + data[4]);
        } catch (Exception ex) {
            executeAlertMessage(button_netmask_cidr, txt_netmask_cidr, ex.getMessage(), ERROR_TICK_LENGTH);
            System.out.println(ex.getMessage());
        }
    }

    @FXML private void convert_requested_hosts() {
        System.out.println("Convert requested hosts");

        try {
            //cidr -- netmask -- class -- hosts -- subnets -- wildcard
            String[] data = new String[6];
            int hosts = Integer.parseInt(txt_requested_hosts.getText());

            data[0] = VLSMSpecializedCalculator.getCIDR(hosts);
            data[1] = VLSMSpecializedCalculator.getNetmask(hosts);
            data[2] = VLSMSpecializedCalculator.getNetmaskClass(data[1]);
            data[3] = VLSMSpecializedCalculator.getValidHost(hosts);
            data[4] = String.valueOf(VLSMSpecializedCalculator.getTotalValidSubnets(Integer.parseInt(data[0])));
            data[5] = VLSMSpecializedCalculator.getWildCardMask(Integer.parseInt(data[0]));

            label_requested_hosts_cidr.setText(LanguageHandler.getKey("converter_hosts_label_CIDR") + ": " + data[0]);
            label_requested_hosts_netmask.setText(LanguageHandler.getKey("converter_hosts_label_Netmask") + ": " + data[1]);
            label_requested_hosts_class.setText(LanguageHandler.getKey("converter_hosts_label_Class") + ": " + data[2]);
            label_requested_hosts_available.setText(LanguageHandler.getKey("converter_hosts_label_Hosts") + ": " + data[3]);
            label_requested_hosts_subnets.setText(LanguageHandler.getKey("converter_hosts_label_Subnets") + ": " + data[4]);
            label_requested_hosts_wildcard.setText(LanguageHandler.getKey("converter_acl_wildcard") + ": " + data[5]);
        } catch (NumberFormatException ex) {
            executeAlertMessage(button_requested_hosts, txt_requested_hosts, LanguageHandler.getKey("converter_hosts_error_invalidHosts"), ERROR_TICK_LENGTH);
            System.out.println(ex.getMessage());
        }
    }


    @FXML private void convert_wildcard() {
        System.out.println("Convert wildcard");
        String wildcard = "";
        String first_ip = txt_wildcard_first_ip.getText();
        String last_ip = txt_wildcard_last_ip.getText();

        try {
            if (NetworkValidator.isBinaryInput(txt_wildcard_first_ip.getText())) {
                first_ip = NetworkConverter.binaryToDecimal(first_ip);
            }
        } catch (Exception ex) {
            executeAlertMessage(button_wildcard, txt_wildcard_first_ip, ex.getMessage(), ERROR_TICK_LENGTH);
        }

        try {
            if (NetworkValidator.isBinaryInput(txt_wildcard_last_ip.getText())) {
                last_ip = NetworkConverter.binaryToDecimal(last_ip);
            }
        } catch (Exception ex) {
            executeAlertMessage(button_wildcard, txt_wildcard_last_ip, ex.getMessage(), ERROR_TICK_LENGTH);
            return;
        }

        wildcard = NetworkConverter.getRequestedWildcard(first_ip, last_ip);
        label_wildcard_result.setText(LanguageHandler.getKey("converter_acl_wildcard")+": " + wildcard);
    }

    private void executeAlertMessage(JFXButton button, JFXTextField text, String exceptionMessage, int tickLength) {
        String previous = text.getText();
        new TickHandler().setBefore(() -> {
            text.setText(exceptionMessage);
            button.setDisable(true);
        }).setAfter(() -> {
            text.setText(previous);
            button.setDisable(false);
        }).setTicks(tickLength).execute();
        text.requestFocus();
    }
}
