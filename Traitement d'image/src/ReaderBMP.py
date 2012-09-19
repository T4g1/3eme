#!/usr/bin/python
# -*- coding: utf-8 -*-

import struct


# Classe gérant le chargement d'un fichier BMP
# Specification du format BMP: http://atlc.sourceforge.net/bmp.html
class Reader:
    def __init__(self, filename):
        self.file = open(filename, 'rb')

        self.infoheader = {}
        self.fileheader = {}
        self.data = []

    # Chargement du header du fichier BMP
    def read_header(self):
        # Deplacement au debut du fichier
        self.file.seek(0,0)

        self.fileheader['signature'] = self.file.read(2)
        self.fileheader['filesize'] = struct.unpack('<L', self.file.read(4))[0]
        self.file.read(4) # Réservé
        self.fileheader['dataoffset'] = struct.unpack('<L', self.file.read(4))[0]

        self.infoheader['size'] = struct.unpack('<L', self.file.read(4))[0]
        self.infoheader['width'] = struct.unpack('<L', self.file.read(4))[0]
        self.infoheader['height'] = struct.unpack('<L', self.file.read(4))[0]
        self.infoheader['planes'] = struct.unpack('<H', self.file.read(2))[0]
        self.infoheader['bitdepth'] = struct.unpack('<H', self.file.read(2))[0]
        self.infoheader['compression'] = struct.unpack('<L', self.file.read(4))[0]
        self.infoheader['imagesize'] = struct.unpack('<L', self.file.read(4))[0]
        self.infoheader['xpixelsm'] = struct.unpack('<L', self.file.read(4))[0]
        self.infoheader['ypixelsm'] = struct.unpack('<L', self.file.read(4))[0]
        self.infoheader['colorsused'] = struct.unpack('<L', self.file.read(4))[0]
        self.infoheader['colorsimportant'] = struct.unpack('<L', self.file.read(4))[0]

    # Chargement de la palette du fichier BMP
    def read_palette(self):
        # Extrait les composantes RGB d'une couleur
        # codée sur un 4bytes (<L dans unpack)
        def rgbtuple(data):
            # Masque &: On ne prend que les 8 derniers bit
            red = data >> 16 & 0xFF
            green = data >> 8 & 0xFF
            blue = data & 0xFF
            
            return (red, green, blue)

        if not (self.fileheader and self.infoheader):
            self.read_header()

        if self.fileheader['signature'] != 'BM':
            raise TypeError('this file is not a bmp file.')

        self.bitdepth = self.infoheader['bitdepth']

        if self.bitdepth > 8:
            raise ValueError('bitmaps with bitdepth greater than 8 doesn\'t have palette.')

        self.file.seek(0x36, 0)
        color_palette = []
        for x in range(2**self.bitdepth):
            color_palette.append(struct.unpack('<L', self.file.read(4))[0])
        
        # Convertit les couleurs entiéres en tuples RGB
        color_palette = map(rgbtuple, color_palette)

        return color_palette

    def read(self):
        if not (self.fileheader and self.infoheader):
            self.read_header()

        if self.fileheader['signature'] != 'BM':
            raise TypeError('this file is not a bmp file.')

        self.bitdepth = self.infoheader['bitdepth']

        if self.infoheader['compression']:
            return TypeError('compressed bitmaps are not supported.')

        if self.data:
            self.data = []

        self.file.seek(self.fileheader['dataoffset'], 0)
        if self.bitdepth <= 8:
            if self.bitdepth < 8:
                sample = 8/self.bitdepth
                mask = 2**self.bitdepth-1
                shift = [x * self.bitdepth for x in reversed(range(sample))]
            height = self.infoheader['height']
            width = self.infoheader['width'] / (8/self.bitdepth)
            for x in range(height):
                row = []
                for y in range(width):
                    pixel = struct.unpack('B', self.file.read(1))[0]
                    if self.bitdepth < 8:
                        row += [mask & (pixel >> i) for i in shift]
                    else:
                        row.append(pixel)
                if width % 4 != 0:
                    self.file.read(4 - (width % 4))
                self.data.append(row)
        elif self.bitdepth == 16:
            return TypeError('bitdepth 16 not supported')
        elif self.bitdepth == 24:
            height = self.infoheader['height']
            width = self.infoheader['width']
            for x in range(height):
                row = []
                for y in range(width):
                    blue = struct.unpack('B', self.file.read(1))[0]
                    green = struct.unpack('B', self.file.read(1))[0]
                    red = struct.unpack('B', self.file.read(1))[0]
                    row.append((red, green, blue))
                self.data.append(row)

        self.data = list(reversed(self.data))
        return self.data

    def read_as_rgb(self):
        if not self.data:
            self.data = self.read()

        if self.bitdepth == 24:
            return self.data

        color_palette = self.read_palette()
        rgb_data = map(lambda row: map(color_palette.__getitem__, row), self.data)

        return rgb_data