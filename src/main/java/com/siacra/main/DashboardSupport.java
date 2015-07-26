import javax.enterprise.context.RequestScoped;
import javax.faces.application.Application;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.panel.Panel;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

@Named
@RequestScoped
public class DashboardSupport {

	protected static final int DEFAULT_COLUMN_COUNT = 3;
	private int columnCount = DEFAULT_COLUMN_COUNT;
	
	private Dashboard dashboard;
        private String titulo;
        private DashboardModel model = new DefaultDashboardModel();
        private FacesContext fc = FacesContext.getCurrentInstance();
        private Application application = fc.getApplication();
        
	public DashboardSupport() {
            
		dashboard = (Dashboard) application.createComponent(fc, "org.primefaces.component.Dashboard", "org.primefaces.component.DashboardRenderer");
		dashboard.setId("dashboard");

		for( int i = 0, n = getColumnCount(); i < n; i++ ) {
			DashboardColumn column = new DefaultDashboardColumn();
			model.addColumn(column);
		}
		dashboard.setModel(model);

		
	}
        
        public void addChildren() {
            
            dashboard.getChildren().clear();
            
            /*int items = 5;
            for( int i = 0, n = items; i < n; i++ ) {
                    Panel panel = (Panel) application.createComponent(fc, "org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer");
                    panel.setId("measure_" + i);
                    panel.setHeader("Dashboard Component " + i);
                    panel.setClosable(true);
                    panel.setToggleable(true);

                    getDashboard().getChildren().add(panel);
                    DashboardColumn column = model.getColumn(i%getColumnCount());
                    column.addWidget(panel.getId());
                    HtmlOutputText text = new HtmlOutputText();
                    text.setValue("Dashboard widget bits!" );

                    panel.getChildren().add(text);
            }*/
            
            Panel panel = (Panel) application.createComponent(fc, "org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer");
            panel.setId("new_element");
            panel.setHeader(getTitulo());
            panel.setClosable(true);
            panel.setToggleable(true);

            getDashboard().getChildren().add(panel);
            DashboardColumn column = model.getColumn(0);
            column.addWidget(panel.getId());
            HtmlOutputText text = new HtmlOutputText();
            text.setValue(getTitulo());

            panel.getChildren().add(text);
            //Loop though calling add for each panel you want to add to the dashboard.
            //dashboard.getChildren().add( yourPanel );
	}
        
	public Dashboard getDashboard() {
		return dashboard;
	}

	public void setDashboard(Dashboard dashboard) {
		this.dashboard = dashboard;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}
        
        public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}