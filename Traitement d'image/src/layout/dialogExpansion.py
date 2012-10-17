# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'dialogExpansion.ui'
#
# Created: Wed Oct 17 11:41:32 2012
#      by: PyQt4 UI code generator 4.9.4
#
# WARNING! All changes made in this file will be lost!

from PyQt4 import QtCore, QtGui

try:
    _fromUtf8 = QtCore.QString.fromUtf8
except AttributeError:
    _fromUtf8 = lambda s: s

class Ui_DialogExpansion(object):
    def setupUi(self, DialogExpansion):
        DialogExpansion.setObjectName(_fromUtf8("DialogExpansion"))
        DialogExpansion.resize(229, 87)
        self.buttonBox = QtGui.QDialogButtonBox(DialogExpansion)
        self.buttonBox.setGeometry(QtCore.QRect(-120, 50, 341, 32))
        self.buttonBox.setOrientation(QtCore.Qt.Horizontal)
        self.buttonBox.setStandardButtons(QtGui.QDialogButtonBox.Cancel|QtGui.QDialogButtonBox.Ok)
        self.buttonBox.setObjectName(_fromUtf8("buttonBox"))
        self.label = QtGui.QLabel(DialogExpansion)
        self.label.setGeometry(QtCore.QRect(110, 20, 51, 16))
        self.label.setObjectName(_fromUtf8("label"))
        self.factor = QtGui.QDoubleSpinBox(DialogExpansion)
        self.factor.setGeometry(QtCore.QRect(160, 20, 62, 22))
        self.factor.setDecimals(1)
        self.factor.setMinimum(1.0)
        self.factor.setMaximum(5.0)
        self.factor.setSingleStep(0.1)
        self.factor.setObjectName(_fromUtf8("factor"))
        self.radioAgrandir = QtGui.QRadioButton(DialogExpansion)
        self.radioAgrandir.setGeometry(QtCore.QRect(10, 10, 82, 17))
        self.radioAgrandir.setChecked(True)
        self.radioAgrandir.setObjectName(_fromUtf8("radioAgrandir"))
        self.buttonGroup = QtGui.QButtonGroup(DialogExpansion)
        self.buttonGroup.setObjectName(_fromUtf8("buttonGroup"))
        self.buttonGroup.addButton(self.radioAgrandir)
        self.radioRetrecir = QtGui.QRadioButton(DialogExpansion)
        self.radioRetrecir.setGeometry(QtCore.QRect(10, 30, 82, 17))
        self.radioRetrecir.setObjectName(_fromUtf8("radioRetrecir"))
        self.buttonGroup.addButton(self.radioRetrecir)

        self.retranslateUi(DialogExpansion)
        QtCore.QObject.connect(self.buttonBox, QtCore.SIGNAL(_fromUtf8("accepted()")), DialogExpansion.accept)
        QtCore.QObject.connect(self.buttonBox, QtCore.SIGNAL(_fromUtf8("rejected()")), DialogExpansion.reject)
        QtCore.QMetaObject.connectSlotsByName(DialogExpansion)

    def retranslateUi(self, DialogExpansion):
        DialogExpansion.setWindowTitle(QtGui.QApplication.translate("DialogExpansion", "Expansion/Extraction", None, QtGui.QApplication.UnicodeUTF8))
        self.label.setText(QtGui.QApplication.translate("DialogExpansion", "Facteur :", None, QtGui.QApplication.UnicodeUTF8))
        self.radioAgrandir.setText(QtGui.QApplication.translate("DialogExpansion", "Agrandir", None, QtGui.QApplication.UnicodeUTF8))
        self.radioRetrecir.setText(QtGui.QApplication.translate("DialogExpansion", "Retrecir", None, QtGui.QApplication.UnicodeUTF8))

