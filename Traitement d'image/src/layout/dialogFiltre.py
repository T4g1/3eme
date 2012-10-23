# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'dialogFiltre.ui'
#
# Created: Mon Oct 22 19:34:19 2012
#      by: PyQt4 UI code generator 4.9.1
#
# WARNING! All changes made in this file will be lost!

from PyQt4 import QtCore, QtGui

try:
    _fromUtf8 = QtCore.QString.fromUtf8
except AttributeError:
    _fromUtf8 = lambda s: s

class Ui_DialogFiltre(object):
    def setupUi(self, DialogFiltre):
        DialogFiltre.setObjectName(_fromUtf8("DialogFiltre"))
        DialogFiltre.resize(198, 115)
        self.buttonBox = QtGui.QDialogButtonBox(DialogFiltre)
        self.buttonBox.setGeometry(QtCore.QRect(-150, 80, 341, 32))
        self.buttonBox.setOrientation(QtCore.Qt.Horizontal)
        self.buttonBox.setStandardButtons(QtGui.QDialogButtonBox.Cancel|QtGui.QDialogButtonBox.Ok)
        self.buttonBox.setObjectName(_fromUtf8("buttonBox"))
        self.label = QtGui.QLabel(DialogFiltre)
        self.label.setGeometry(QtCore.QRect(10, 40, 81, 16))
        self.label.setObjectName(_fromUtf8("label"))
        self.quantityBox = QtGui.QSpinBox(DialogFiltre)
        self.quantityBox.setGeometry(QtCore.QRect(100, 40, 42, 22))
        self.quantityBox.setMinimum(1)
        self.quantityBox.setMaximum(6)
        self.quantityBox.setObjectName(_fromUtf8("quantityBox"))
        self.label_2 = QtGui.QLabel(DialogFiltre)
        self.label_2.setGeometry(QtCore.QRect(150, 40, 46, 13))
        self.label_2.setObjectName(_fromUtf8("label_2"))
        self.label_3 = QtGui.QLabel(DialogFiltre)
        self.label_3.setGeometry(QtCore.QRect(10, 10, 46, 13))
        self.label_3.setObjectName(_fromUtf8("label_3"))
        self.filtreBox = QtGui.QComboBox(DialogFiltre)
        self.filtreBox.setGeometry(QtCore.QRect(60, 10, 81, 22))
        self.filtreBox.setObjectName(_fromUtf8("filtreBox"))
        self.filtreBox.addItem(_fromUtf8(""))
        self.filtreBox.addItem(_fromUtf8(""))
        self.filtreBox.addItem(_fromUtf8(""))
        self.filtreBox.addItem(_fromUtf8(""))
        self.filtreBox.addItem(_fromUtf8(""))
        self.filtreBox.addItem(_fromUtf8(""))
        self.filtreBox.addItem(_fromUtf8(""))
        self.filtreBox.addItem(_fromUtf8(""))

        self.retranslateUi(DialogFiltre)
        QtCore.QObject.connect(self.buttonBox, QtCore.SIGNAL(_fromUtf8("accepted()")), DialogFiltre.accept)
        QtCore.QObject.connect(self.buttonBox, QtCore.SIGNAL(_fromUtf8("rejected()")), DialogFiltre.reject)
        QtCore.QMetaObject.connectSlotsByName(DialogFiltre)

    def retranslateUi(self, DialogFiltre):
        DialogFiltre.setWindowTitle(QtGui.QApplication.translate("DialogFiltre", "Appliquer un filtre", None, QtGui.QApplication.UnicodeUTF8))
        self.label.setText(QtGui.QApplication.translate("DialogFiltre", "Appliquer le filtre ", None, QtGui.QApplication.UnicodeUTF8))
        self.label_2.setText(QtGui.QApplication.translate("DialogFiltre", "fois", None, QtGui.QApplication.UnicodeUTF8))
        self.label_3.setText(QtGui.QApplication.translate("DialogFiltre", "Filtre :", None, QtGui.QApplication.UnicodeUTF8))
        self.filtreBox.setItemText(0, QtGui.QApplication.translate("DialogFiltre", "Médian", None, QtGui.QApplication.UnicodeUTF8))
        self.filtreBox.setItemText(1, QtGui.QApplication.translate("DialogFiltre", "Moyen", None, QtGui.QApplication.UnicodeUTF8))
        self.filtreBox.setItemText(2, QtGui.QApplication.translate("DialogFiltre", "Gaussien", None, QtGui.QApplication.UnicodeUTF8))
        self.filtreBox.setItemText(3, QtGui.QApplication.translate("DialogFiltre", "Laplacien", None, QtGui.QApplication.UnicodeUTF8))
        self.filtreBox.setItemText(4, QtGui.QApplication.translate("DialogFiltre", "Sobel", None, QtGui.QApplication.UnicodeUTF8))
        self.filtreBox.setItemText(5, QtGui.QApplication.translate("DialogFiltre", "Roberts", None, QtGui.QApplication.UnicodeUTF8))
        self.filtreBox.setItemText(6, QtGui.QApplication.translate("DialogFiltre", "Kirsh", None, QtGui.QApplication.UnicodeUTF8))
        self.filtreBox.setItemText(7, QtGui.QApplication.translate("DialogFiltre", "Prewitt", None, QtGui.QApplication.UnicodeUTF8))

