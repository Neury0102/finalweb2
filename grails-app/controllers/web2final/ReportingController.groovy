package web2final



import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter
import web2final.entidades_reportes.ArticuloFactura;

class ReportingController {

    def dataSource

    def index() {
        try {
            String reportName, jrxmlFileName, dotJasperFileName
            jrxmlFileName = "jasper_report_template"
            reportName = grailsApplication.mainContext.getResource('reports/' + jrxmlFileName + '.jrxml').file.getAbsoluteFile()
            dotJasperFileName = grailsApplication.mainContext.getResource('reports/' + jrxmlFileName + '.jasper').file.getAbsoluteFile()

            // Report parameter

            def factura = Factura.findById(1)
            println factura
            def dataList = ArticuloFactura.getArticulosFactura(factura)
            println dataList

            JRBeanCollectionDataSource beanColDataSource = new
                    JRBeanCollectionDataSource(dataList);

            Map<String, Object> reportParam = new HashMap<String, Object>()
            reportParam.put("detailsData", 'lol')

            reportParam.put("customerName", factura.cliente.nombre + " " + factura.cliente.apellido)
            reportParam.put("customerEmail", factura.cliente.email)
            reportParam.put("invoiceNumber", factura.id)
            reportParam.put("invoiceAmount",factura.total())
            reportParam.put("invoiceDate",new Date())
            reportParam.put("comprobante", factura.comprobante)
            // compiles jrxml
            JasperCompileManager.compileReportToFile(reportName);
            // fills compiled report with parameters and a connection
            JasperPrint print = JasperFillManager.fillReport(dotJasperFileName, reportParam, beanColDataSource);

            ByteArrayOutputStream  pdfStream = new ByteArrayOutputStream();

            // exports report to pdf
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, pdfStream); // your output goes here

            exporter.exportReport();
            //println 'pdfStream = '+pdfStream.size()

        } catch (Exception e) {

            throw new RuntimeException("It's not possible to generate the pdf report.", e);
        } finally {
            render(file: pdfStream.toByteArray(), contentType: 'application/pdf')
           //render(file: pdfStream.toByteArray(), fileName: 'DownloadReport.pdf', contentType: 'application/pdf')
        }
    }
}
