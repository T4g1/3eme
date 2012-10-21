# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'dialogThresholdMultiple.ui'
#
# Created: Fri Sep 28 20:56:13 2012
#      by: PyQt4 UI code generator 4.9.4
#
# WARNING! All changes made in this file will be lost!

from PyQt4 import QtCore, QtGui

try:
    _fromUtf8 = QtCore.QString.fromUtf8
except AttributeError:
    _fromUtf8 = lambda s: s

class Ui_DialogThresholdMultiple(object):
    def setupUi(self, DialogThresholdMultiple):
        DialogThresholdMultiple.setObjectName(_fromUtf8("DialogThresholdMultiple"))
        DialogThresholdMultiple.resize(425, 296)
        self.buttonBox = QtGui.QDialogButtonBox(DialogThresholdMultiple)
        self.buttonBox.setGeometry(QtCore.QRect(70, 260, 341, 32))
        self.buttonBox.setOrientation(QtCore.Qt.Horizontal)
        self.buttonBox.setStandardButtons(QtGui.QDialogButtonBox.Cancel|QtGui.QDialogButtonBox.Ok)
        self.buttonBox.setObjectName(_fromUtf8("buttonBox"))
        self.label_2 = QtGui.QLabel(DialogThresholdMultiple)
        self.label_2.setGeometry(QtCore.QRect(20, 160, 46, 13))
        self.label_2.setObjectName(_fromUtf8("label_2"))
        self.apercu = QtGui.QLabel(DialogThresholdMultiple)
        self.apercu.setGeometry(QtCore.QRect(70, 100, 151, 161))
        self.apercu.setText(_fromUtf8(""))
        self.apercu.setObjectName(_fromUtf8("apercu"))
        self.label = QtGui.QLabel(DialogThresholdMultiple)
        self.label.setGeometry(QtCore.QRect(10, 10, 71, 16))
        self.label.setObjectName(_fromUtf8("label"))
        self.label_4 = QtGui.QLabel(DialogThresholdMultiple)
        self.label_4.setGeometry(QtCore.QRect(10, 40, 71, 16))
        self.label_4.setObjectName(_fromUtf8("label_4"))
        self.label_5 = QtGui.QLabel(DialogThresholdMultiple)
        self.label_5.setGeometry(QtCore.QRect(10, 70, 81, 16))
        self.label_5.setObjectName(_fromUtf8("label_5"))
        self.seuilSlider1 = QtGui.QSlider(DialogThresholdMultiple)
        self.seuilSlider1.setGeometry(QtCore.QRect(90, 10, 271, 19))
        self.seuilSlider1.setMaximum(255)
        self.seuilSlider1.setOrientation(QtCore.Qt.Horizontal)
        self.seuilSlider1.setObjectName(_fromUtf8("seuilSlider1"))
        self.seuilSlider2 = QtGui.QSlider(DialogThresholdMultiple)
        self.seuilSlider2.setGeometry(QtCore.QRect(90, 40, 271, 19))
        self.seuilSlider2.setMaximum(255)
        self.seuilSlider2.setOrientation(QtCore.Qt.Horizontal)
        self.seuilSlider2.setObjectName(_fromUtf8("seuilSlider2"))
        self.seuilSlider3 = QtGui.QSlider(DialogThresholdMultiple)
        self.seuilSlider3.setGeometry(QtCore.QRect(90, 70, 271, 19))
        self.seuilSlider3.setMaximum(255)
        self.seuilSlider3.setOrientation(QtCore.Qt.Horizontal)
        self.seuilSlider3.setObjectName(_fromUtf8("seuilSlider3"))
        self.seuilBox1 = QtGui.QSpinBox(DialogThresholdMultiple)
        self.seuilBox1.setGeometry(QtCore.QRect(370, 10, 42, 22))
        self.seuilBox1.setMaximum(255)
        self.seuilBox1.setObjectName(_fromUtf8("seuilBox1"))
        self.seuilBox2 = QtGui.QSpinBox(DialogThresholdMultiple)
        self.seuilBox2.setGeometry(QtCore.QRect(370, 40, 42, 22))
        self.seuilBox2.setMaximum(255)
        self.seuilBox2.setObjectName(_fromUtf8("seuilBox2"))
        self.seuilBox3 = QtGui.QSpinBox(DialogThresholdMultiple)
        self.seuilBox3.setGeometry(QtCore.QRect(370, 70, 42, 22))
        self.seuilBox3.setMaximum(255)
        self.seuilBox3.setObjectName(_fromUtf8("seuilBox3"))

        self.retranslateUi(DialogThresholdMultiple)
        QtCore.QObject.connect(self.buttonBox, QtCore.SIGNAL(_fromUtf8("accepted()")), DialogThresholdMultiple.accept)
        QtCore.QObject.connect(self.buttonBox, QtCore.SIGNAL(_fromUtf8("rejected()")), DialogThresholdMultiple.reject)
        QtCore.QMetaObject.connectSlotsByName(DialogThresholdMultiple)

    def retranslateUi(self, DialogThresholdMultiple):
        DialogThresholdMultiple.setWindowTitle(QtGui.QApplication.translate("DialogThresholdMultiple", "Seuillage multiple", None, QtGui.QApplication.UnicodeUTF8))
        self.label_2.setText(QtGui.QApplication.translate("DialogThresholdMultiple", "Apercu :", None, QtGui.QApplication.UnicodeUTF8))
        self.label.setText(QtGui.QApplication.translate("DialogThresholdMultiple", "Premier seuil :", None, QtGui.QApplication.UnicodeUTF8))
        self.label_4.setText(QtGui.QApplication.translate("DialogThresholdMultiple", "Second seuil :", None, QtGui.QApplication.UnicodeUTF8))
        self.label_5.setText(QtGui.QApplication.translate("DialogThresholdMultiple", "Troisiéme seuil :", None, QtGui.QApplication.UnicodeUTF8))

