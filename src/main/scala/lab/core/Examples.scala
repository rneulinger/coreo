package lab.core

object Examples {
  trait _Person extends _Dlg:
    val FirstName = TXT()
    val LastName = TXT()
    @coreo.Ui("on field")
    @coreo.Return
    val Ok = RET()
    @coreo.Return
    val Cancel = RET()
}
