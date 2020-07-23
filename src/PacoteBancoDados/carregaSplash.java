package PacoteBancoDados;
/**
 *
 * @author vitor
 */
public class carregaSplash {
    
    public static void main(String[] args) {
        JFSplash splash = new JFSplash();

        splash.setVisible(true);

        JFLogin janelaLogin = new JFLogin();

        try {

            for (int i = 0; i <= 100; i++) {
                
                Thread.sleep(30);
                splash.lblLoad.setText(Integer.toString(i) + "%");
                splash.prbSplash.setValue(i);
                if (i == 100) {
                    splash.dispose();
                    janelaLogin.setVisible(true);

                }
            }

        } catch (Exception e) {
        }

    }
    
}
