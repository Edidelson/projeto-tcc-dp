package br.com.tcc.util;

import com.zap.arca.JADecimalFormatField;
import com.zap.arca.JANumberFormatField;
import com.zap.arca.util.DateUtils;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.*;
import java.util.HashSet;
import java.util.Locale;
import javax.swing.*;
import javax.swing.table.TableModel;
import org.hibernate.cfg.Configuration;
import table.model.ArcaTableModel;

/**
 * Classe destinada a métodos que auxiliaram em outras classes.
 * 
 * @author Edidelson
 * @version Util 0.1, 01/05/10
 */
public class Util {

    // Referência para instância única
    private static Util instance = null;
    public static final NumberFormat NF_TONELADA;
    public static final NumberFormat NF_VALORES;
    public static final NumberFormat NF_GENERICO;
    public static final DateFormat DF_BANCO;
    public static final DateFormat DF_PADRAO;
    public static final DateFormat DF_HORA;
    
    static {     
        
        NF_TONELADA = NumberFormat.getInstance(new Locale("pt", "BR"));
        NF_TONELADA.setMinimumFractionDigits(3);
        NF_TONELADA.setMaximumFractionDigits(3);
        
        NF_GENERICO = NumberFormat.getInstance(new Locale("pt", "BR"));
        
        DF_BANCO = new SimpleDateFormat("yyyy-MM-dd", new Locale("pt", "BR"));
        DF_PADRAO = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
        DF_HORA = new SimpleDateFormat("HH:mm:ss", new Locale("pt", "BR"));
        
        NF_VALORES = NumberFormat.getInstance(new Locale("pt", "BR"));
        NF_VALORES.setMinimumFractionDigits(5);
        NF_VALORES.setMaximumFractionDigits(5);
    }
    
    /**
     * Retorna uma string com o valor formatado com o número de 
     * inteiros e decimais informados
     * @param numInteiros
     * @param numDecimais
     * @param valor
     * @return 
     */
    public static String formatarValores(int numInteiros, int numDecimais, BigDecimal valor) {
        NF_GENERICO.setMinimumFractionDigits(numDecimais);
        NF_GENERICO.setMaximumFractionDigits(numDecimais);
        NF_GENERICO.setMinimumIntegerDigits(numInteiros);
        NF_GENERICO.setMaximumIntegerDigits(numInteiros);
        return NF_GENERICO.format(valor);
    }
    
    /**
     * Retorna uma String com o valor informado com o número de decimais inviado
     * @param numDecimais
     * @param valor
     * @return 
     */
    public static String formatarValores(int numDecimais, BigDecimal valor) {
        NF_GENERICO.setMinimumFractionDigits(numDecimais);
        NF_GENERICO.setMaximumFractionDigits(numDecimais);
        return NF_GENERICO.format(valor);
    }                   
        
    public static String formatarCodigo(int codigo) {
        return NF_VALORES.format(codigo).replace(".", "");
    }
    
    public static String formatarCodigo(int codigo, int numCaracteres) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(0);
        nf.setMinimumFractionDigits(0);
        nf.setMinimumIntegerDigits(numCaracteres);
        nf.setMaximumIntegerDigits(numCaracteres);
        String formatado = nf.format(codigo);
        formatado = formatado.replace(".", "");
        formatado = formatado.replace(",", "");
        return formatado;
    }
    
    public String capitalizeAllWords(String s) {
        String minusculo = s.toLowerCase();
        String[] palavras = minusculo.split(" ");
        StringBuilder resultado = new StringBuilder();
        for(String p: palavras) {
            if(p.length() < 2) {
                continue;
            }
            resultado.append(p.toUpperCase().charAt(0));
            resultado.append(p.substring(1, p.length()));
            resultado.append(" ");            
        }
        return resultado.toString();
    }
    
    public void salvarConfiguracao(Configuration cfg, File arquivo) {
        try {
            FileOutputStream fos = new FileOutputStream(arquivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(cfg);
            oos.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public Configuration restaurarConfiguracao(File arquivo) {
        try {
            FileInputStream fis = new FileInputStream(arquivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Configuration cfg = (Configuration) ois.readObject();
            return cfg;
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }    

    public static String formatarTelefone(String telefone) {
        if(telefone == null) {
            return "";
        }
        String telefone1 = telefone;
        if( (telefone != null) && (telefone.length() == 10) ) {
            telefone1 = "(" + telefone.substring(0, 2) + ")" + " " + telefone.substring(2, 6) + "-" + telefone.substring(6, 10);
        }
        return telefone1;
    }

    public String formatarCep(String cep) {
        if(cep == null) {
            return "";
        }
        String cep1 = cep;
        if(cep1.length() == 8){
            cep1 = cep.substring(0, 5) + "-" + cep.substring(5, 8);
        }
        return cep1;
    }

    public String formatarCnh(String cnh) {
        if(cnh == null) {
            return "";
        }
        return cnh;
    }

    public String formatarCpf(String cpf) {
        if(cpf == null) {
            return "";
        }
        String cpf1 = cpf;
        if(cpf.length() == 11) {
            cpf1 = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
        }
        if(cpf1.equals("   .   .   -  ")) {
            cpf1 = "";
        }
        return cpf1;
    }

    public String formatarCarteiraDeTrabalho(String ct) {
        if(ct == null) {
            return "";
        }
        String ct1 = ct;
        if(ct.length() == 12) {
            ct1 = ct.substring(0, 6) + "-" + ct.substring(6, 10) + "-" + ct.substring(10, 12);
        }
        return ct1;
    }

    public String formatarRg(String rg) {
        if(rg == null) {
            return "";
        }
        return rg;
    }

    /**
     * Adiciona os caracteres especiais de uma string contendo um CNPJ
     * @param cnpj - String
     * @return
     */
    public static String formatarCnpj(String cnpj) {
        String cnpj1 = cnpj;
        if(cnpj.length() == 14) {
            cnpj1 = cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8) + "/" +
                    cnpj.substring(8, 12) + "-" + cnpj.substring(12, 14);
        }
        return cnpj1;
    }

    /**
     * Método estático para retornar apenas uma referencia do objeto util.
     * @return Util
     */
    public static Util getUtil() {
        return (instance == null) ? new Util() : instance;
    } 
    
    /**
     * Verifica se o valor informado está entre 0 e 100
     * @param ftf - JANumberFormatField que possui o número a verificar
     */
    public void verificarPorcentagemInformada(JComponent ftf) {                    
        if (ftf instanceof JADecimalFormatField) {
            if (((JADecimalFormatField) ftf).getValue().doubleValue() > 100) {
                ((JADecimalFormatField) ftf).setValue(BigDecimal.valueOf(100));
            }        
        }
        if (ftf instanceof JANumberFormatField) {
            if (((JANumberFormatField) ftf).getValue().doubleValue() > 100) {
                ((JANumberFormatField) ftf).setValue(BigDecimal.valueOf(100));
            }        
        }
    }  
    
    /**
     * Armazena em um arquivo de Texto o stacktrace de uma exceção junto com a data e hora em que ocorreu
     * @param e - Exception a ser armazenada
     */
    public static void logException(Exception e) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
        String cabecalho = "\n---------" + df.format(new java.util.Date()) + "----------\n";
        File file = new File("Logs");
        file.mkdirs();
        file = new File("Logs/Exceptions.txt");
        try {
            FileWriter writer = new FileWriter(file, true);
            PrintWriter pw = new PrintWriter(writer, true);
            pw.print(cabecalho);
            e.printStackTrace(pw);
            pw.close();
            writer.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao armazenar exceção");
        }
    }         
    
    /**
     * Desativa todos os componentes de um Array
     * @param components - Component[] Array com os componentes a desativar
     */
    private static void desativarCampos(Component[] components) {
        for(Component c: components) {
            c.setEnabled(false);
            if(c instanceof JANumberFormatField) {
                JANumberFormatField ftf = (JANumberFormatField) c;
                ftf.setText("");
            }
        }
    }

    /**
     * Ativa todos os campos de um Array de acordo com o produto selecionado
     * @param components - Component[] Array com os componentes a ativar
     */
    private static void ativarCampos(Component[] components) {
        for(Component c: components) {
            c.setEnabled(true);
        }
    }
    /**
     * Método para inserir um icone na aplicação com a passagem do parâmento com o nome do diretório da classe
     * dentro do jar.
     * @param frame o frame que deverá conter o icone.
     */
    public void inserirIconeAplicacao(JFrame frame) {

        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/imagens/estatistica.png")));

    }

    /**
     * Método para inserir um icone na aplicação com a passagem do parâmento com o nome do diretório da classe
     * dentro do jar.
     * @param frame o frame que deverá conter o icone.
     */
    public void inserirIconeAplicacao(JDialog frame) {

        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/imagens/ZAP.jpg")));

    }   

    /**
     * Método para validar o CNPJ ou CPF digitado.
     * @param CNPJ_CPF string com o valor a ser validado
     * @return verdadeiro se correto, falso caso contrario.
     */
    public static boolean validarCNPJCPF(final String CNPJ_CPF) {

        if (CNPJ_CPF.length() == 11) { //CPF

            int acumulador1 = 0;
            int acumulador2 = 0;
            int resto = 0;

            StringBuffer resultado = new StringBuffer();

            String digitoVerificadorCPF = CNPJ_CPF.substring((CNPJ_CPF.length() - 2),
                    CNPJ_CPF.length());

            for (int i = 1; i < (CNPJ_CPF.length() - 1); i++) {
                acumulador1 += (11 - i) * Integer.valueOf(CNPJ_CPF.substring((i - 1), i));
                acumulador2 += (12 - i) * Integer.valueOf(CNPJ_CPF.substring((i - 1), i));
            }

            resto = acumulador1 % 11;

            if (resto < 2) {
                acumulador2 += 2;
                resultado.append(2);
            } else {
                acumulador2 += 2 * (11 - resto);
                resultado.append((11 - resto));
            }

            resto = acumulador2 % 11;

            if (resto < 2) {
                resultado.append(0);
            } else {
                resultado.append((11 - resto));
            }

            return resultado.toString().equals(digitoVerificadorCPF);
        } else if (CNPJ_CPF.length() == 14) { //CNPJ

            int acumulador = 0;
            int digito = 0;
            StringBuffer CNPJ = new StringBuffer();
            char[] CNPJCharArray = CNPJ_CPF.toCharArray();

            CNPJ.append(CNPJ_CPF.substring(0, 12));

            for (int i = 0; i < 4; i++) {
                if (((CNPJCharArray[i] - 48) >= 0)
                        && ((CNPJCharArray[i] - 48) <= 9)) {
                    acumulador += (CNPJCharArray[i] - 48) * (6 - (i + 1));
                }
            }

            for (int i = 0; i < 8; i++) {
                if (((CNPJCharArray[i + 4] - 48) >= 0)
                        && ((CNPJCharArray[i + 4] - 48) <= 9)) {
                    acumulador += (CNPJCharArray[i + 4] - 48) * (10 - (i + 1));
                }
            }

            digito = 11 - (acumulador % 11);

            CNPJ.append((digito == 10 || digito == 11) ? "0" : digito);

            acumulador = 0;

            for (int i = 0; i < 5; i++) {
                if (((CNPJCharArray[i] - 48) >= 0)
                        && ((CNPJCharArray[i] - 48) <= 9)) {
                    acumulador += (CNPJCharArray[i] - 48) * (7 - (i + 1));
                }
            }

            for (int i = 0; i < 8; i++) {
                if (((CNPJCharArray[i + 5] - 48) >= 0)
                        && ((CNPJCharArray[i + 5] - 48) <= 9)) {
                    acumulador += (CNPJCharArray[i + 5] - 48) * (10 - (i + 1));
                }
            }

            digito = 11 - (acumulador % 11);

            CNPJ.append((digito == 10 || digito == 11) ? "0" : digito);

            return CNPJ.toString().equals(CNPJ_CPF);
        }

        return false;
    }// Fim Método valida CNPJ/CPF  
    
    
    /**
     * Faz a tecla enter funcionar como a tecla Tab para mudar de campos
     * @param c conteiner principal
     */
    public void proximoComEnter(Component c) {
        HashSet conj = new HashSet(c.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        c.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
    }
    
    public void setEnterButton(Component component) {

        if (component instanceof JButton) {
            HashSet con = new HashSet(component.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
            con.remove(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
            component.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, con);
        }

        if (component instanceof JFrame) {
            for (Component index : ((JFrame) component).getContentPane().getComponents()) {
                setEnterButton(index);
            }
        }

        if (component instanceof JDialog) {
            for (int i = 0; i < ((JDialog) component).getComponentCount(); i++) {
                Component c = ((JDialog) component).getComponent(i);
                setEnterButton(c);
            }
        }

        if (component instanceof JTabbedPane) {
            for (int i = 0; i < ((JTabbedPane) component).getComponentCount(); i++) {
                Component c = ((JTabbedPane) component).getComponent(i);
                setEnterButton(c);
            }
        }

        if (component instanceof JLayeredPane) {
            for (int i = 0; i < ((JLayeredPane) component).getComponentCount(); i++) {
                Component c = ((JLayeredPane) component).getComponent(i);
                setEnterButton(c);
            }
        }

        if (component instanceof JPanel) {
            for (int i = 0; i < ((JPanel) component).getComponentCount(); i++) {
                Component c = ((JPanel) component).getComponent(i);
                setEnterButton(c);
            }
        }
        if (component instanceof JInternalFrame) {
            for (int i = 0; i < ((JInternalFrame) component).getComponentCount(); i++) {
                Component c = ((JInternalFrame) component).getComponent(i);
                setEnterButton(c);
            }
        }
        if (component instanceof JRootPane) {
            for (int i = 0; i < ((JRootPane) component).getComponentCount(); i++) {
                Component c = ((JRootPane) component).getComponent(i);
                setEnterButton(c);
            }
        }
    }
}