# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'dialogAutre.ui'
#
# Created: Fri Sep 28 20:56:15 2012
#      by: PyQt4 UI code generator 4.9.4
#
# WARNING! All changes made in this file will be lost!

from PyQt4 import QtCore, QtGui

try:
    _fromUtf8 = QtCore.QString.fromUtf8
except AttributeError:
    _fromUtf8 = lambda s: s

class Ui_DialogAutre(object):
    def setupUi(self, DialogAutre):
        DialogAutre.setObjectName(_fromUtf8("DialogAutre"))
        DialogAutre.resize(176, 94)
        self.buttonBox = QtGui.QDialogButtonBox(DialogAutre)
        self.buttonBox.setGeometry(QtCore.QRect(-170, 60, 341, 32))
        self.buttonBox.setOrientation(QtCore.Qt.Horizontal)
        self.buttonBox.setStandardButtons(QtGui.QDialogButtonBox.Cancel|QtGui.QDialogButtonBox.Ok)
        self.buttonBox.setObjectName(_fromUtf8("buttonBox"))
        self.quantityBox = QtGui.QSpinBox(DialogAutre)
        self.quantityBox.setGeometry(QtCore.QRect(60, 40, 42, 22))
        self.quantityBox.setMinimum(1)
        self.quantityBox.setMaximum(6)
        self.quantityBox.setObjectName(_fromUtf8("quantityBox"))
        self.label = QtGui.QLabel(DialogAutre)
        self.label.setGeometry(QtCore.QRect(10, 40, 81, 16))
        self.label.setObjectName(_fromUtf8("label"))
        self.label_3 = QtGui.QLabel(DialogAutre)
        self.label_3.setGeometry(QtCore.QRect(10, 10, 46, 13))
        self.label_3.setObjectName(_fromUtf8("label_3"))
        self.label_2 = QtGui.QLabel(DialogAutre)
        self.label_2.setGeometry(QtCore.QRect(110, 40, 46, 13))
        self.label_2.setObjectName(_fromUtf8("label_2"))
        self.filtreBox = QtGui.QComboBox(DialogAutre)
        self.filtreBox.setGeometry(QtCore.QRect(60, 10, 81, 22))
        self.filtreBox.setObjectName(_fromUtf8("filtreBox"))
        self.filtreBox.addItem(_fromUtf8(""))
        self.filtreBox.addItem(_fromUtf8(""))
        self.filtreBox.addItem(_fromUtf8(""))
        self.filtreBox.addItem(_fromUtf8(""))

        self.retranslateUi(DialogAutre)
        QtCore.QObject.connect(self.buttonBox, QtCore.SIGNAL(_fromUtf8("accepted()")), DialogAutre.accept)
        QtCore.QObject.connect(self.buttonBox, QtCore.SIGNAL(_fromUtf8("rejected()")), DialogAutre.reject)
        QtCore.QMetaObject.connectSlotsByName(DialogAutre)

    def retranslateUi(self, DialogAutre):
        DialogAutre.setWindowTitle(QtGui.QApplication.translate("DialogAutre", "Appliquer une érosion/dilatation/ouverture/fermeture", None, QtGui.QApplication.UnicodeUTF8))
        self.label.setText(QtGui.QApplication.translate("DialogAutre", "Appliquer", None, QtGui.QApplication.UnicodeUTF8))
        self.label_3.setText(QtGui.QApplication.translate("DialogAutre", "Filtre :", None, QtGui.QApplication.UnicodeUTF8))
        self.label_2.setText(QtGui.QApplication.translate("DialogAutre", "fois", None, QtGui.QApplication.UnicodeUTF8))
        self.filtreBox.setItemText(0, QtGui.QApplication.translate("DialogAutre", "Erosion", None, QtGui.QApplication.UnicodeUTF8))
        self.filtreBox.setItemText(1, QtGui.QApplication.translate("DialogAutre", "Dilatation", None, QtGui.QApplication.UnicodeUTF8))
        self.filtreBox.setItemText(2, QtGui.QApplication.translate("DialogAutre", "Ouverture", None, QtGui.QApplication.UnicodeUTF8))
        self.filtreBox.setItemText(3, QtGui.QApplication.translate("DialogAutre", "Fermeture", None, QtGui.QApplication.UnicodeUTF8))

